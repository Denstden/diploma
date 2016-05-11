package exception;

public class NoQuestionException extends Exception{

    @Override
    public  String getMessage(){return "Unable to generate the required number of questions.";}
}
