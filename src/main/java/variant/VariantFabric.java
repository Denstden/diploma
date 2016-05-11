package variant;

import exception.NoQuestionException;
import exception.WrongDataQuestionException;
import question.AbstractQuestion;
import question.fabric.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class VariantFabric {
    private QF qf;
    private int count;
    private ArrayList<QuestionFabric> questionFabrics;
    private String preambula;
    private int countVariant;
    private int countQuestionType1;
    private int countQuestionType2;
    private int countQuestionType3;
    private int countQuestionType4;

    public VariantFabric(String source, String variantPreambula, int count) throws FileNotFoundException, WrongDataQuestionException {
        this.count = count;
        preambula = variantPreambula;
        countVariant = 1;
        qf = new QF(source);
    }

    public VariantFabric(String source, String variantPreambula, int countQuestionType1, int countQuestionType2, int countQuestionType3, int countQuestionType4) throws FileNotFoundException, WrongDataQuestionException {
        this.countQuestionType1 = countQuestionType1;
        this.countQuestionType2 = countQuestionType2;
        this.countQuestionType3 = countQuestionType3;
        this.countQuestionType4 = countQuestionType4;
        preambula = variantPreambula;

        countVariant = 1;
        questionFabrics = new ArrayList<QuestionFabric>();
        questionFabrics.add(new QuestionFabricType1(source));
        questionFabrics.add(new QuestionFabricType2(source));
        questionFabrics.add(new QuestionFabricType3(source));
        questionFabrics.add(new QuestionFabricType4(source));
    }

    public Variant gVariant() throws WrongDataQuestionException{
        ArrayList<AbstractQuestion> arrayList= new ArrayList<>();
        for (int i=0;i<count;i++){
            AbstractQuestion question = qf.getQuestion();
            if (!arrayList.contains(question))
                arrayList.add(question);
            else
                count++;
        }
        return new Variant(Integer.toString(countVariant++), preambula, arrayList);
    }

    public Variant getVariant() throws FileNotFoundException, NoQuestionException, WrongDataQuestionException {
        ArrayList<AbstractQuestion> arrayList= new ArrayList<>();
        for (int i=0;i< countQuestionType1;i++){
            arrayList.add(questionFabrics.get(0).getQuestion());
        }
        for (int i=0;i< countQuestionType2;i++){
            arrayList.add(questionFabrics.get(1).getQuestion());
        }
        for (int i=0;i< countQuestionType3;i++){
            arrayList.add(questionFabrics.get(2).getQuestion());
        }
        for (int i=0;i< countQuestionType4;i++){
            arrayList.add(questionFabrics.get(3).getQuestion());
        }
        Collections.shuffle(arrayList);

        return new Variant(Integer.toString(countVariant++), preambula, arrayList);
    }
}