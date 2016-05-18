package parser.parsed_data;

import format.FormatSettings;

public class ParsedDataType1 extends ParsedData{
    public int col_answers;
    public int col_pos_answers;
    public int col_neg_answers;
    public String[] answ_pos;
    public String[] answ_neg;

    public ParsedDataType1(){
        setFormatSettings(new FormatSettings());
    }

    @Override
    public void print() {
        System.out.println("PRINT PARSED DATA TYPE 1");
        System.out.println(getPreambula()+"\n"+col_answers+"\n"+col_pos_answers+"\n"+col_neg_answers);
        for (String s:answ_pos){
            System.out.print(s + " ");
        }
        System.out.println();
        for (String s:answ_neg){
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
