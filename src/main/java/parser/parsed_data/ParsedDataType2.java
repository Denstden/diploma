package parser.parsed_data;

import format.FormatSettings;

public class ParsedDataType2 extends ParsedData{
    public String correctAnswer;
    public String[] answers;

    public ParsedDataType2(){setFormatSettings(new FormatSettings());}

    @Override
    public void print(){
        System.out.println("PRINT PARSED DATA TYPE 2");
        System.out.println(getPreambula());
        System.out.println();
    }
}