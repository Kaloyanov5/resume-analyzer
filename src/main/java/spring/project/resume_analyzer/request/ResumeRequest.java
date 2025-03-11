package spring.project.resume_analyzer.request;

import org.springframework.web.multipart.MultipartFile;

public class ResumeRequest {
    private MultipartFile file;
    private String email;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
