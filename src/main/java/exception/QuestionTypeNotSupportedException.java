package exception;

public class QuestionTypeNotSupportedException extends Exception {
    @Override
    public  String getMessage(){return "Question type not supported.";}
}
