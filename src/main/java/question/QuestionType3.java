package question;

import format.FormatSettings;

public class QuestionType3 extends AbstractQuestion {

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setFormatSettings(FormatSettings formatSettings) {
        this.formatSettings = formatSettings;
    }

    @Override
    public void print() {
        System.out.print(getString());
    }

    @Override
    public String  getString(){
        String s = "";
        s+=question+"\r\n";
        for (int i=0;i<formatSettings.getCount();i++)
            s+="\r\n";
        return s;
    }
}