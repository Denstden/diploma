package parser.config.question;

import java.util.List;

import parser.config.variant.Estimation;

public class QuestionData {
    private List<QuestionConfig> questionConfigs;
    private Estimation estimation;

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

    @Override
    public String toString() {
        return "QuestionData{" +
                "estimation=" + estimation +
                ", questionConfigs=" + questionConfigs +
                '}';
    }
}
