package spring.project.resume_analyzer.request;

import java.util.List;
import java.util.Map;

public class AnalyzationRequest {
    private int totalScore;
    private Map<String, Integer> categoryScores;
    private Feedback feedback;
    private List<String> jobMatchSuggestions;

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Map<String, Integer> getCategoryScores() {
        return categoryScores;
    }

    public void setCategoryScores(Map<String, Integer> categoryScores) {
        this.categoryScores = categoryScores;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public List<String> getJobMatchSuggestions() {
        return jobMatchSuggestions;
    }

    public void setJobMatchSuggestions(List<String> jobMatchSuggestions) {
        this.jobMatchSuggestions = jobMatchSuggestions;
    }

    public static class Feedback {
        private List<String> strengths;
        private List<String> areasForImprovement;

        public List<String> getStrengths() {
            return strengths;
        }

        public void setStrengths(List<String> strengths) {
            this.strengths = strengths;
        }

        public List<String> getAreasForImprovement() {
            return areasForImprovement;
        }

        public void setAreasForImprovement(List<String> areasForImprovement) {
            this.areasForImprovement = areasForImprovement;
        }
    }
}
