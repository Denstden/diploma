package parser.config.question.question_types;

public class QuestionYesNoConfigData extends AbstractQuestionConfigData {
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionCheckboxConfigData{" +
                "hashtag=" + hashTag +
                ", preambula=" + preambula +
                ", answer=" + answer +
                ", formatElements=" + formatElements +
                '}';
    }
}
