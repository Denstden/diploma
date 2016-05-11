import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


class Variant{
    private String name;
    private String preambula;
    private ArrayList<Question> questionList;

    public Variant(String name, String preambula, ArrayList<Question> questionList){
        this.name = name;
        this.preambula = preambula;
        this.questionList = questionList;
    }

    public void print() {
        System.out.println("Variant " + name);
        System.out.print(preambula);
        int i=1;
        for (Question q : questionList) {
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
        out.println("Variant "+name);
        out.print(preambula);
        int i=1;
        for (Question q : questionList) {
            out.print(i+". "+q.getString());
            i++;
        }
        out.close();
    }
}