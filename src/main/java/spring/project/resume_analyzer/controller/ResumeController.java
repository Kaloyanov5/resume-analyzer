package spring.project.resume_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.project.resume_analyzer.request.ResumeRequest;
import spring.project.resume_analyzer.service.ResumeService;

@RestController
@RequestMapping("/api")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/resume")
    public ResponseEntity<?> analyzeResume(@ModelAttribute ResumeRequest resumeRequest) {
        return resumeService.analyzeResume(resumeRequest);
    }
}
