package question;

import question.format.FormatSettings;

public class CheckboxQuestion extends AbstractQuestion {

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public void setFormatSettings(FormatSettings formatSettings) {
        this.formatSettings = formatSettings;
    }

    @Override
    public void print(){
        System.out.print(toString());
    }

    @Override
    public String toString() {
        String s = "";
        s += question + "\r\n\t";
        switch (formatSettings.getType()) {
        case COLUMNS: {
            int i;
            for (i = 1; i < answers.length + 1; i++) {
                s += "(_) " + answers[i - 1] + "\t";
                if (i % formatSettings.getCount() == 0 && i != answers.length) {
                    s += "\r\n\t";
                }
            }
            break;
        }
        case ROWS: {
            int k = answers.length / formatSettings.getCount();
            int i;
            for (i = 1; i < answers.length + 1; i++) {
                s += "(_) " + answers[i - 1] + "\t";
                if (i % k == 0 && i != answers.length) {
                    s += "\r\n\t";
                }
            }
            break;
        }
        }
        s += "\r\n";
        return s;
    }
}
