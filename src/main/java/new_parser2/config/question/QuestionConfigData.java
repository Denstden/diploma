package new_parser2.config.question;

import new_parser2.config.question.question_types.AbstractQuestionConfigData;

import java.util.List;

public class QuestionConfigData {
    private List<AbstractQuestionConfigData> questionConfigDatas;
    private String globalPreambula = "";

    public List<AbstractQuestionConfigData> getQuestionConfigDatas() {
        return questionConfigDatas;
    }

    public void setQuestionConfigDatas(List<AbstractQuestionConfigData> questionConfigDatas) {
        this.questionConfigDatas = questionConfigDatas;
    }

    public String getGlobalPreambula() {
        return globalPreambula;
    }

    public void setGlobalPreambula(String globalPreambula) {
        this.globalPreambula = globalPreambula;
    }

    @Override
    public String toString() {
        return "QuestionConfigData{" +
                "questionConfigDatas=" + questionConfigDatas +
                ", globalPreambula='" + globalPreambula + '\'' +
                '}';
    }
}
