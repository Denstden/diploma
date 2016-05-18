package control_struct;

import builder.QuestionBuilder;
import exception.WrongDataQuestionException;
import parser.Parser;
import parser.parsed_data.*;
import question.*;

import java.io.FileNotFoundException;


public class ControlStruct {
    private QuestionBuilder questionBuilder;
    private Parser parser;

    public ControlStruct(String source) throws WrongDataQuestionException, FileNotFoundException {
        parser = new Parser(source);
        parser.parse();
        questionBuilder = new QuestionBuilder();
    }

    public AbstractQuestion getNext() throws WrongDataQuestionException {
        ParsedData parsedData = parser.getNextParsedData();
        if (parsedData==null){
            parser.setIter();
            parsedData =parser.getNextParsedData();
        }
        if (parsedData.getClass().equals(ParsedDataType1.class))
            return questionBuilder.getQuestion((ParsedDataType1)parsedData);
        else if (parsedData.getClass().equals(ParsedDataType2.class))
            return questionBuilder.getQuestion((ParsedDataType2)parsedData);
        else if (parsedData.getClass().equals(ParsedDataType3.class))
            return questionBuilder.getQuestion((ParsedDataType3)parsedData);
        else
            return questionBuilder.getQuestion((ParsedDataType4)parsedData);
    }

    public QuestionType1 getNextType1() throws WrongDataQuestionException {
        ParsedDataType1 parsedData = parser.getNextParsedDataType1();
        if (parsedData==null){
            parser.shuffle();
            parser.setIter();
            parsedData =parser.getNextParsedDataType1();
        }
        return questionBuilder.getQuestion(parsedData);
    }

    public QuestionType2 getNextType2(){
        ParsedDataType2 parsedData = parser.getNextParsedDataType2();
        if (parsedData==null){
            parser.shuffle();
            parser.setIter();
            parsedData =parser.getNextParsedDataType2();
        }

        return questionBuilder.getQuestion(parsedData);
    }

    public QuestionType3 getNextType3(){
        ParsedDataType3 parsedData = parser.getNextParsedDataType3();
        if (parsedData==null){
            parser.shuffle();
            parser.setIter();
            parsedData =parser.getNextParsedDataType3();
        }
        return questionBuilder.getQuestion(parsedData);
    }

    public QuestionType4 getNextType4() throws WrongDataQuestionException {
        ParsedDataType4 parsedData = parser.getNextParsedDataType4();
        if (parsedData==null){
            parser.shuffle();
            parser.setIter();
            parsedData =parser.getNextParsedDataType4();
        }
        return questionBuilder.getQuestion(parsedData);
    }
}
