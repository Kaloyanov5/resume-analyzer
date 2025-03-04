package spring.project.resume_analyzer.request;

import org.springframework.web.multipart.MultipartFile;

public class ResumeRequest {
    private MultipartFile resume;
    private String email;

    public MultipartFile getResume() {
        return resume;
    }

    public void setResume(MultipartFile resume) {
        this.resume = resume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
