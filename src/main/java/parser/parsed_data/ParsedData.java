package parser.parsed_data;

import format.FormatSettings;

public abstract class ParsedData{
    public String preambula;
    public FormatSettings formatSettings;
    public abstract void print();
}