package parser.parsed_data;

import format.FormatSettings;

public class ParsedDataType3 extends ParsedData{

    public ParsedDataType3(){setFormatSettings(new FormatSettings());}

    @Override
    public void print() {
        System.out.println("PRINT PARSED DATA TYPE 3");
        System.out.println(getPreambula());
        System.out.println();
    }
}