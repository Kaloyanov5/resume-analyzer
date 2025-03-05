package spring.project.resume_analyzer.service;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class UtilService{

//    @Autowired
//    private JavaMailSender emailSender;

    public String extractTextFromFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            return new Tika().parseToString(inputStream);
        } catch (IOException | TikaException e) {
            return null;
        }
    }

    public void sendResultsToEmail(String to, String subject, String text) {
        // empty for now
    }
}
