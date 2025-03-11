package spring.project.resume_analyzer.request;

import java.util.List;
import java.util.Map;

public class AnalyzationRequest {
    private int total_score;
    private Map<String, Integer> category_scores;
    private Feedback feedback;
    private List<String> job_match_suggestions;

    public int getTotalScore() {
        return total_score;
    }

    public void setTotalScore(int total_score) {
        this.total_score = total_score;
    }

    public Map<String, Integer> getCategoryScores() {
        return category_scores;
    }

    public void setCategoryScores(Map<String, Integer> category_scores) {
        this.category_scores = category_scores;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public List<String> getJobMatchSuggestions() {
        return job_match_suggestions;
    }

    public void setJobMatchSuggestions(List<String> job_match_suggestions) {
        this.job_match_suggestions = job_match_suggestions;
    }

    public static class Feedback {
        private List<String> strengths;
        private List<String> areas_for_improvement;

        public List<String> getStrengths() {
            return strengths;
        }

        public void setStrengths(List<String> strengths) {
            this.strengths = strengths;
        }

        public List<String> getAreasForImprovement() {
            return areas_for_improvement;
        }

        public void setAreasForImprovement(List<String> areas_for_improvement) {
            this.areas_for_improvement = areas_for_improvement;
        }
    }
}
