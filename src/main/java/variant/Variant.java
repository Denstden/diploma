package variant;

import question.AbstractQuestion;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class Variant{
    private String name;
    private String preambula;
    private List<AbstractQuestion> questionList;

    public Variant() {
    }

    public Variant(String name, String preambula, List<AbstractQuestion> questionList){
        this.name = name;
        this.preambula = preambula;
        this.questionList = questionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreambula() {
        return preambula;
    }

    public void setPreambula(String preambula) {
        this.preambula = preambula;
    }

    public List<AbstractQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<AbstractQuestion> questionList) {
        this.questionList = questionList;
    }

    public void print() {
        System.out.println("Variant " + name);
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