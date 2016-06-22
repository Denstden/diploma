package question;

import question.format.FormatSettings;

public class EsseQuestion extends AbstractQuestion {

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setFormatSettings(FormatSettings formatSettings) {
        this.formatSettings = formatSettings;
    }

    @Override
    public void print() {
        System.out.print(toString());
    }

    @Override
    public String  toString(){
        String s = "";
        s+=question+"\r\n\t";
        for (int i=0;i<formatSettings.getCount();i++)
            s+="\r\n";
        return s;
    }
}
