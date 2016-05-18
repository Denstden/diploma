package new_builder;

import new_parser2.config.question.QuestionData;
import new_parser2.config.variant.VariantConfig;
import question.AbstractQuestion;
import variant.Variant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by storo on 5/18/2016.
 */
public class VariantBuilder {
    private VariantConfig config;

    public VariantConfig getConfig() {
        return config;
    }

    public void setConfig(VariantConfig config) {
        this.config = config;
    }

    public Variant build(Integer number){
        Variant variant = new Variant();
        variant.setName(""+number);
        String header = "";
        for (String line : config.getHeader()) {
            header += line+"\n";
        }
        variant.setPreambula(header);
        List<AbstractQuestion> questions = new ArrayList<>();
        QuestionBuilder builder = new QuestionBuilder();
        for (QuestionData questionData: config.getQuestions()){
            builder.setQuestionData(questionData);
            questions.add(builder.build());
        }
        variant.setQuestionList(questions);
        variant.print();
        return variant;
    }
}
