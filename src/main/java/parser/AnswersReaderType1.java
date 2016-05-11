package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnswersReaderType1{
    private String fname;
    private String[] answers;

    public AnswersReaderType1(String filename)throws FileNotFoundException {
        fname=filename;
        read_answers();
    }

    private void read_answers() throws FileNotFoundException{
        Scanner in = new Scanner(new File(fname));
        ArrayList<String> list = new ArrayList<>();
        while (in.hasNext()){
            list.add(in.nextLine());
        }
        answers = new String[list.size()];
        int i=0;
        for (String s:list)
            answers[i++]=s;
        in.close();
    }

    public String[] getAnswers(){
        return answers;
    }
}