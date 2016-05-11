package parser;

import parser.parsed_data.ParsedData;

import java.util.LinkedList;

public class ParsedDatas{
    public LinkedList<ParsedData> parsedDataLinkedList;

    public ParsedDatas(){
        parsedDataLinkedList = new LinkedList<>();
    }

    public void  add(ParsedData parsedData){
        parsedDataLinkedList.add(parsedData);
    }
}

