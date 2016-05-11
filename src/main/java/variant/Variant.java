package variant;

import question.AbstractQuestion;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Variant{
    private String name;
    private String preambula;
    private ArrayList<AbstractQuestion> questionList;

    public Variant(String name, String preambula, ArrayList<AbstractQuestion> questionList){
        this.name = name;
        this.preambula = preambula;
        this.questionList = questionList;
    }

    public void print() {
        System.out.println("variant.Variant " + name);
        System.out.print(preambula);
        int i=1;
        for (AbstractQuestion q : questionList) {
            System.out.print(i+". ");
            i++;
            q.print();
        }
    }

    public void toFile(String pathToFolder) throws IOException {
        File file = new File(pathToFolder+"\\"+"Variant_"+name+".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        out.println("variant.Variant "+name);
        out.print(preambula);
        int i=1;
        for (AbstractQuestion q : questionList) {
            out.print(i+". "+q.getString());
            i++;
        }
        out.close();
    }
}