package parser.parsed_data;

import format.FormatSettings;

public class ParsedDataType4 extends ParsedData{
    public int col_answers;
    public String answ_pos;
    public String[] answ_neg;

    public ParsedDataType4(){formatSettings = new FormatSettings();}

    @Override
    public void print() {
        System.out.println("PRINT PARSED DATA TYPE 4");
        System.out.println(preambula+"\n"+col_answers);
        System.out.println(answ_pos);
        for (String s:answ_neg){
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
