package spring.project.resume_analyzer.entity;

import jakarta.persistence.*;

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String filename;
    @Column(nullable = false)
    private String fileType;

    @Lob
    private String extractedText;

    private double score; // 0-100

    @Lob
    private String aiFeedback;

    public Resume() {
    }

    public Resume(String email, String filename, String fileType, String extractedText, double score, String aiFeedback) {
        this.email = email;
        this.filename = filename;
        this.fileType = fileType;
        this.extractedText = extractedText;
        this.score = score;
        this.aiFeedback = aiFeedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAiFeedback() {
        return aiFeedback;
    }

    public void setAiFeedback(String aiFeedback) {
        this.aiFeedback = aiFeedback;
    }
}
