package variant;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import question.AbstractQuestion;


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
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String res = "";
        res += "Варіант " + name + "\r\n" + preambula;
        int i=1;
        for (AbstractQuestion q : questionList) {
            res += i+". ";
            i++;
            res += q.toString();
        }
        return res;
    }

    public void toFile(String pathToFolder) throws IOException {
        File file = new File(pathToFolder+"\\"+"Variant_"+name+".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        out.print(toString());
        out.close();
    }
}
