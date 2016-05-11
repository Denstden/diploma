package question;

import format.FormatSettings;

public class QuestionType1 extends AbstractQuestion {

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public void setFormatSettings(FormatSettings formatSettings) {
        this.formatSettings = formatSettings;
    }

    public void print(){
        System.out.print(getString());
    }

    public String getString(){
        String s = "";
        s+=question+"\r\n";
        switch (formatSettings.getType()) {
            case COLUMN: {
                for (int i = 0; i < answers.length; i++)
                    if (i==answers.length-1)
                        s+="(_) " + answers[i];
                    else
                        s+="(_) " + answers[i]+"\r\n";
                break;
            }
            case COLUMNS: {
                int i;
                for (i = 1; i < answers.length+1; i++) {
                    s+="(_) " + answers[i-1]+"\t";
                    if (i%formatSettings.getCount()==0 && i!=answers.length)
                    {
                        s+="\r\n";
                    }

                }
                break;
            }
            case ROW: {
                for (int i = 0; i < answers.length; i++)
                    s+="(_) " + answers[i] + "\t";
                break;
            }
            case ROWS: {
                int k= answers.length/formatSettings.getCount();
                int i;
                for (i = 1; i < answers.length+1; i++) {
                    s+="(_) " + answers[i-1]+"\t";
                    if (i%k==0 && i!=answers.length)
                    {
                        s+="\r\n";
                    }
                }
                break;
            }
        }
        s+="\r\n";
        return s;
    }
}
