import java.util.Arrays;

abstract class Question{
    protected String question;
    protected String[] answers;
    protected FormatSettings formatSettings;

    abstract public String getString();

    abstract public void print();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (question != null ? !question.equals(question1.question) : question1.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (answers != null ? Arrays.hashCode(answers) : 0);
        result = 31 * result + (formatSettings != null ? formatSettings.hashCode() : 0);
        return result;
    }
}


class ConcreteQuestionType1 extends Question{

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


class ConcreteQuestionType2 extends Question {

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


class ConcreteQuestionType3 extends Question {

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


class ConcreteQuestionType4 extends Question {

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
    public String  getString(){
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
