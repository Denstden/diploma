package new_parser2.config.question;


/**
 * Created by storo on 5/18/2016.
 */
public class QuestionConfig {
    private static final Integer DEF_COUNT_OF_QUESTIONS = 1;
    private QuestionConfigData questionConfigData;
    private Integer countOfQuestions;

    public QuestionConfigData getQuestionConfigData() {
        return questionConfigData;
    }

    public void setQuestionConfigData(QuestionConfigData questionConfigData) {
        this.questionConfigData = questionConfigData;
    }

    public Integer getCountOfQuestions() {
        return countOfQuestions;
    }

    public void setCountOfQuestions(Integer countOfQuestions) {
        this.countOfQuestions = countOfQuestions;
    }

    public static Integer getDefCountOfQuestions() {
        return DEF_COUNT_OF_QUESTIONS;
    }

    @Override
    public String toString() {
        return "QuestionConfig{" +
                "questionConfigData='" + questionConfigData + '\'' +
                ", countOfQuestions=" + countOfQuestions +
                '}';
    }
}
