package new_parser2.config.question.question_types;

import java.util.List;

/**
 * Created by storo on 5/18/2016.
 */
public class QuestionRadioButtonConfigData extends AbstractQuestionConfigData {
    private Integer countAnswers;
    private List<String> correctAnswers;
    private List<String> incorrectAnswers;

    public Integer getCountAnswers() {
        return countAnswers;
    }

    public void setCountAnswers(Integer countAnswers) {
        this.countAnswers = countAnswers;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    @Override
    public String toString() {
        return "QuestionCheckboxConfigData{" +
                "hashtag=" + hashTag +
                ", preambula=" + preambula +
                ", countAnswers=" + countAnswers +
                ", sourceCorrectAnswers='" + correctAnswers + '\'' +
                ", sourceIncorrectAnswers='" + incorrectAnswers + '\'' +
                ", formatElements=" + formatElements +
                '}';
    }
}
