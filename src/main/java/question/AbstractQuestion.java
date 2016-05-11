package question;

import format.FormatSettings;

import java.util.Arrays;

public abstract class AbstractQuestion {
    protected String question;
    protected String[] answers;
    protected FormatSettings formatSettings;

    abstract public String getString();

    abstract public void print();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractQuestion question1 = (AbstractQuestion) o;

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