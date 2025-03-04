package spring.project.resume_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.project.resume_analyzer.request.ResumeRequest;
import spring.project.resume_analyzer.service.ResumeService;

@RestController
@RequestMapping("/api")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/resume")
    public ResponseEntity<?> analyzeResume(@RequestBody ResumeRequest resumeRequest) {
        return resumeService.analyzeResume(resumeRequest);
    }
}
