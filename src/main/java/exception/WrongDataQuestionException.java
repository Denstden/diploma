package exception;

public class WrongDataQuestionException extends Exception{

    @Override
    public String getMessage() {
        return "Wrong data question.";
    }
}

