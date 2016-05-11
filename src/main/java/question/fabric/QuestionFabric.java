package question.fabric;

import control_struct.ControlStruct;
import exception.NoQuestionException;
import exception.WrongDataQuestionException;
import question.AbstractQuestion;

import java.io.FileNotFoundException;

public abstract class QuestionFabric{
    protected ControlStruct controlStruct;
    public abstract AbstractQuestion getQuestion() throws WrongDataQuestionException, FileNotFoundException, NoQuestionException;
}
