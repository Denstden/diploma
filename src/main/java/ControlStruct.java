import java.io.FileNotFoundException;


class ControlStruct {
    private Builder builder;
    private Parser parser;

    public ControlStruct(String source) throws WrongDataQuestEx, FileNotFoundException {
        parser = new Parser(source);
        parser.parse();
        builder = new Builder();
    }

    public Question getNext() throws WrongDataQuestEx {
        ParsedData parsedData = parser.getNextParsedData();
        if (parsedData==null){
            parser.setIter();
            parsedData =parser.getNextParsedData();
        }
        if (parsedData.getClass().equals(new ParsedDataType1().getClass()))
            return builder.getQuestion((ParsedDataType1)parsedData);
        else if (parsedData.getClass().equals(new ParsedDataType2().getClass()))
            return builder.getQuestion((ParsedDataType2)parsedData);
        else if (parsedData.getClass().equals(new ParsedDataType3().getClass()))
            return builder.getQuestion((ParsedDataType3)parsedData);
        else
            return builder.getQuestion((ParsedDataType4)parsedData);
    }

    public ConcreteQuestionType1 getNextType1() throws WrongDataQuestEx {
        ParsedDataType1 parsedData = parser.getNextParsedDataType1();
        if (parsedData==null){
            parser.shuffle();
            parser.setIter();
            parsedData =parser.getNextParsedDataType1();
        }
        return builder.getQuestion(parsedData);
    }

    public ConcreteQuestionType2 getNextType2(){
        ParsedDataType2 parsedData = parser.getNextParsedDataType2();
        if (parsedData==null){
            parser.shuffle();
            parser.setIter();
            parsedData =parser.getNextParsedDataType2();
        }

        return builder.getQuestion(parsedData);
    }

    public ConcreteQuestionType3 getNextType3(){
        ParsedDataType3 parsedData = parser.getNextParsedDataType3();
        if (parsedData==null){
            parser.shuffle();
            parser.setIter();
            parsedData =parser.getNextParsedDataType3();
        }
        return builder.getQuestion(parsedData);
    }

    public ConcreteQuestionType4 getNextType4() throws WrongDataQuestEx {
        ParsedDataType4 parsedData = parser.getNextParsedDataType4();
        if (parsedData==null){
            parser.shuffle();
            parser.setIter();
            parsedData =parser.getNextParsedDataType4();
        }
        return builder.getQuestion(parsedData);
    }
}
