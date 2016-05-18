package new_parser2.config.question;

import new_parser2.config.variant.Estimation;

import java.util.List;

/**
 * Created by storo on 5/18/2016.
 */
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
