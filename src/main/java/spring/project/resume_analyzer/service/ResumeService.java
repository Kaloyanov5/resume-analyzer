package spring.project.resume_analyzer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.project.resume_analyzer.entity.Resume;
import spring.project.resume_analyzer.repository.ResumeRepository;
import spring.project.resume_analyzer.request.AnalyzationRequest;
import spring.project.resume_analyzer.request.ResumeRequest;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private GeminiService geminiService;
    @Autowired
    private UtilService utilService;
    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<?> analyzeResume(ResumeRequest resumeRequest) {
        try {
            MultipartFile file = resumeRequest.getFile();
            if (file.isEmpty())
                return ResponseEntity.badRequest().body("Please upload a valid resume file.");
            String email = resumeRequest.getEmail();

            String fileContent = utilService.extractTextFromFile(file);
            if (fileContent == null)
                return ResponseEntity.internalServerError().body("Error extracting text from file!");

            ResponseEntity<?> aiResponse = geminiService.geminiAnalyzeResume(fileContent);
            if (aiResponse.getStatusCode() != HttpStatus.OK)
                return ResponseEntity.status(aiResponse.getStatusCode()).body(aiResponse.getBody());

            AnalyzationRequest responseObject = objectMapper.readValue(aiResponse.getBody().toString(), AnalyzationRequest.class);
            utilService.sendResultsToEmail(email, responseObject);

            Resume resumeObject = new Resume(
                    email,
                    file.getOriginalFilename(),
                    "pdf",
                    fileContent,
                    responseObject.getTotalScore(),
                    responseObject.toString()
            );
            resumeRepository.save(resumeObject);

            return ResponseEntity.ok("Resume analyzed successfully!");
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
