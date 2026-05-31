package library_fiek.services;

public class AIRecommendationService {
    public String getRecommendation() {
        return LanguageService.get("ai.recommendation");
    }
}