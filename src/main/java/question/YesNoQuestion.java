package question;

import question.format.FormatSettings;

public class YesNoQuestion extends AbstractQuestion {

    public void setQuestion(String question) {
        this.preamble = question;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public void setFormatSettings(FormatSettings formatSettings) {
        this.formatSettings = formatSettings;
    }

    @Override
    public void print() {
        System.out.print(toString());
    }

    @Override
    public String toString() {
        String s = "";
        s+= preamble +"\r\n\t";
        switch (formatSettings.getType()){
            case ROWS:{
                s+="(_)"+answers[0]+"\t\t(_)"+answers[1]+"\r\n";
                break;}
            case COLUMNS:{
                s+="(_)"+answers[0]+"\r\n\t(_)"+answers[1]+"\r\n";
                break;}
        }
        return s;
    }


}
