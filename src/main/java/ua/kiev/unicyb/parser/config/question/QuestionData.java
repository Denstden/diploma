package ua.kiev.unicyb.parser.config.question;

import java.util.List;

import ua.kiev.unicyb.parser.config.variant.Estimation;

public class QuestionData {
    private List<QuestionConfig> questionConfigs;
    private Estimation estimation;
    private List<String> hashtags;

    public List<QuestionConfig> getQuestionConfigs() {
        return questionConfigs;
    }

    public void setQuestionConfigs(List<QuestionConfig> questionConfigs) {
        this.questionConfigs = questionConfigs;
    }

    public Estimation getEstimation() {
        return estimation;
    }

    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public String toString() {
        return "QuestionData{" +
                "questionConfigs=" + questionConfigs +
                ", estimation=" + estimation +
                ", hashtags=" + hashtags +
                '}';
    }
}
