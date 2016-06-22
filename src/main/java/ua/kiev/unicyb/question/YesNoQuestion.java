package ua.kiev.unicyb.question;

public class YesNoQuestion extends AbstractQuestion {

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
