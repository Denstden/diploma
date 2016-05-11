import java.io.FileNotFoundException;


abstract class QuestionFabric{
    protected ControlStruct controlStruct;
    public abstract Question getQuestion() throws WrongDataQuestEx, FileNotFoundException, NoQuestionEx;
}


class QF extends QuestionFabric{

    public QF(String path) throws FileNotFoundException, WrongDataQuestEx {
        controlStruct = new ControlStruct(path);
    }

    @Override
    public Question getQuestion() throws WrongDataQuestEx {
        return controlStruct.getNext();
    }
}


class QuestionFabricType1 extends QuestionFabric{

    public QuestionFabricType1(String pathToFolder) throws FileNotFoundException, WrongDataQuestEx {
        controlStruct = new ControlStruct(pathToFolder);
    }

    @Override
    public Question getQuestion() throws WrongDataQuestEx, FileNotFoundException, NoQuestionEx {
        return  controlStruct.getNextType1();
    }

}


class QuestionFabricType2 extends QuestionFabric{

    public QuestionFabricType2(String pathToFolder) throws FileNotFoundException, WrongDataQuestEx {
        controlStruct = new ControlStruct(pathToFolder);
    }

    @Override
    public Question getQuestion() throws WrongDataQuestEx, FileNotFoundException, NoQuestionEx {
        return  controlStruct.getNextType2();
    }
}


class QuestionFabricType3 extends QuestionFabric{

    public QuestionFabricType3(String pathToFolder) throws FileNotFoundException, WrongDataQuestEx {
        controlStruct = new ControlStruct(pathToFolder);
    }

    @Override
    public Question getQuestion() throws WrongDataQuestEx, FileNotFoundException, NoQuestionEx {
        return  controlStruct.getNextType3();
    }
}


class QuestionFabricType4 extends QuestionFabric{

    public QuestionFabricType4(String pathToFolder) throws FileNotFoundException, WrongDataQuestEx {
        controlStruct = new ControlStruct(pathToFolder);
    }

    @Override
    public Question getQuestion() throws WrongDataQuestEx, FileNotFoundException, NoQuestionEx {
        return  controlStruct.getNextType4();
    }
}
