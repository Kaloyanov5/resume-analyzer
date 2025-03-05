package spring.project.resume_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.project.resume_analyzer.repository.ResumeRepository;
import spring.project.resume_analyzer.request.ResumeRequest;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private UtilService utilService;

    public ResponseEntity<?> analyzeResume(ResumeRequest resumeRequest) {
        MultipartFile resume = resumeRequest.getResume();
        if (resume.isEmpty())
            return ResponseEntity.badRequest().body("Please upload a valid resume file.");
        String email = resumeRequest.getEmail();

        String fileContent = utilService.extractTextFromFile(resume);
        if (fileContent == null)
            return ResponseEntity.internalServerError().body("Error extracting text from file!");



        return ResponseEntity.ok("");
    }
}
