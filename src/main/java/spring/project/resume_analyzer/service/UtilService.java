package spring.project.resume_analyzer.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.project.resume_analyzer.request.AnalyzationRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class UtilService{

    @Autowired
    private JavaMailSender emailSender;

    public String extractTextFromFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            return new Tika().parseToString(inputStream);
        } catch (IOException | TikaException e) {
            return null;
        }
    }

    public void sendResultsToEmail(String to, AnalyzationRequest analyzationRequest) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("kaloyanovbojidar@gmail.com");
            helper.setTo(to);
            helper.setSubject("Your Resume Analysis is Ready!");
            helper.setText(formatRequestToString(analyzationRequest), true);

            emailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String formatRequestToString(AnalyzationRequest analyzationRequest) {
        StringBuilder emailContent = new StringBuilder();
        Integer[] categoryWiseStats = { 25, 20, 25, 15, 15 };
        int currentIndex = 0;

        emailContent.append("<html><body>");
        emailContent.append("<p>Dear Candidate,</p>");
        emailContent.append("<p>Thank you for submitting your resume for analysis. Below is the detailed evaluation:</p>");

        emailContent
                .append("<h2>📊 Overall Score: ")
                .append(analyzationRequest.getTotalScore())
                .append("/100</h2>");

        emailContent.append("<h3>📌 Category-wise Breakdown:</h3><ul>");
        for (Map.Entry<String, Integer> entry : analyzationRequest.getCategoryScores().entrySet()) {
            emailContent
                    .append("<li><strong>")
                    .append(entry.getKey())
                    .append(":</strong> ")
                    .append(entry.getValue())
                    .append("/")
                    .append(categoryWiseStats[currentIndex])
                    .append("</li>");
            currentIndex++;
        }

        emailContent.append("</ul>");

        emailContent.append("<h3>✅ Strengths:</h3><ul>");
        for (String strength : analyzationRequest.getFeedback().getStrengths())
            emailContent
                    .append("<li>")
                    .append(strength)
                    .append("</li>");
        emailContent.append("</ul>");

        emailContent.append("<h3>⚠️ Areas for Improvement:</h3><ul>");
        for (String improvement : analyzationRequest.getFeedback().getAreasForImprovement())
            emailContent
                    .append("<li>")
                    .append(improvement)
                    .append("</li>");
        emailContent.append("</ul>");

        emailContent.append("<h3>🔍 Job Match Suggestions:</h3><ul>");
        for (String job : analyzationRequest.getJobMatchSuggestions())
            emailContent
                    .append("<li>")
                    .append(job)
                    .append("</li>");
        emailContent.append("</ul>");

        emailContent.append("<p>We hope this analysis helps you improve your resume and increase your chances of securing your desired job.</p>");
        emailContent.append("</body></html>");

        return emailContent.toString();
    }
}
