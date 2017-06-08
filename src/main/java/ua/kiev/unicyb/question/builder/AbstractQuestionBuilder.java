package ua.kiev.unicyb.question.builder;

import ua.kiev.unicyb.parser.config.question.question_types.AbstractQuestionConfigData;
import ua.kiev.unicyb.parser.config.variant.Estimation;
import ua.kiev.unicyb.question.AbstractQuestion;

import java.util.List;

/**
 * @Author Denys Storozhenko.
 */
public abstract class AbstractQuestionBuilder {
    Estimation estimation;

    AbstractQuestionConfigData configData;

    String globalPreamble;

    public abstract AbstractQuestion build();

    public Estimation getEstimation() {
        return estimation;
    }

    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }

    public AbstractQuestionConfigData getConfigData() {
        return configData;
    }

    public void setConfigData(AbstractQuestionConfigData configData) {
        this.configData = configData;
    }

    public String getGlobalPreamble() {
        return globalPreamble;
    }

    public void setGlobalPreamble(String globalPreamble) {
        this.globalPreamble = globalPreamble;
    }
}
