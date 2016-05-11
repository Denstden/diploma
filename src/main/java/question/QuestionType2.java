package question;

import format.FormatSettings;

public class QuestionType2 extends AbstractQuestion {

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
    public void print() {
        System.out.print(getString());
    }

    @Override
    public String getString() {
        String s = "";
        s+=question+"\r\n";
        switch (formatSettings.getType()){
            case ROW:{
                s+=answers[0]+"\t\t"+answers[1]+"\r\n";
                break;}
            case COLUMN:{
                s+=answers[0]+"\r\n"+answers[1]+"\r\n";
                break;}
        }
        return s;
    }


}