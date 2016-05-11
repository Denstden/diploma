package question.fabric;

import control_struct.ControlStruct;
import exception.WrongDataQuestionException;
import question.AbstractQuestion;

import java.io.FileNotFoundException;

public class QF extends QuestionFabric{

    public QF(String path) throws FileNotFoundException, WrongDataQuestionException {
        controlStruct = new ControlStruct(path);
    }

    @Override
    public AbstractQuestion getQuestion() throws WrongDataQuestionException {
        return controlStruct.getNext();
    }
}