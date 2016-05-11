package question.fabric;

import control_struct.ControlStruct;
import exception.NoQuestionException;
import exception.WrongDataQuestionException;
import question.AbstractQuestion;

import java.io.FileNotFoundException;

public class QuestionFabricType1 extends QuestionFabric{

    public QuestionFabricType1(String pathToFolder) throws FileNotFoundException, WrongDataQuestionException {
        controlStruct = new ControlStruct(pathToFolder);
    }

    @Override
    public AbstractQuestion getQuestion() throws WrongDataQuestionException, FileNotFoundException, NoQuestionException {
        return  controlStruct.getNextType1();
    }

}