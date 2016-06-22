package variant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import parser.config.question.QuestionConfigData;
import parser.config.question.QuestionData;
import parser.config.question.question_types.AbstractQuestionConfigData;
import parser.config.question.question_types.QuestionCheckboxConfigData;
import parser.config.question.question_types.QuestionEsseConfigData;
import parser.config.question.question_types.QuestionRadioButtonConfigData;
import parser.config.question.question_types.QuestionYesNoConfigData;
import parser.config.variant.VariantConfig;
import question.AbstractQuestion;
import question.builder.CheckboxQuestionBuilder;
import question.builder.EsseQuestionBuilder;
import question.builder.RadioButtonQuestionBuilder;
import question.builder.YesNoQuestionBuilder;
import question.factory.CheckboxQuestionFactory;
import question.factory.EsseQuestionFactory;
import question.factory.RadioButtonQuestionFactory;
import question.factory.YesNoQuestionFactory;

public class VariantFabric {
    private VariantConfig config;
    private Random random = new Random();

    public VariantConfig getConfig() {
        return config;
    }

    public void setConfig(VariantConfig config) {
        this.config = config;
    }

    public Variant getVariant(Integer number){
        Variant variant = new Variant();
        variant.setName(""+number);
        String header = "";
        for (String line : config.getHeader()) {
            header += line+"\n";
        }
        variant.setPreambula(header);
        List<AbstractQuestion> questions = new ArrayList<>();
        CheckboxQuestionFactory checkboxQuestionFactory = new CheckboxQuestionFactory();
        EsseQuestionFactory esseQuestionFactory = new EsseQuestionFactory();
        YesNoQuestionFactory yesNoQuestionFactory = new YesNoQuestionFactory();
        RadioButtonQuestionFactory radioButtonQuestionFactory = new RadioButtonQuestionFactory();
        CheckboxQuestionBuilder checkboxQuestionBuilder = new CheckboxQuestionBuilder();
        EsseQuestionBuilder esseQuestionBuilder = new EsseQuestionBuilder();
        YesNoQuestionBuilder yesNoQuestionBuilder = new YesNoQuestionBuilder();
        RadioButtonQuestionBuilder radioButtonQuestionBuilder = new RadioButtonQuestionBuilder();
        QuestionConfigData questionConfigData;
        String globalPreambula;
        List<AbstractQuestionConfigData> configDatas;
        AbstractQuestionConfigData configData;

        for (QuestionData questionData: config.getQuestions()){
            if (questionData.getQuestionConfigs().size()>1){
                System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
             //   return buildCombinedQuestion(questionData.getQuestionConfigs());
            }
            questionConfigData= questionData.getQuestionConfigs().get(0).getQuestionConfigData();
            globalPreambula = questionConfigData.getGlobalPreambula();
            configDatas = questionConfigData.getQuestionConfigDatas();
            configData = configDatas.get(random.nextInt(configDatas.size()));
            if (configData.getClass().equals(QuestionCheckboxConfigData.class)){
                checkboxQuestionBuilder.setConfigData(configData);
                checkboxQuestionBuilder.setGlobalPreambula(globalPreambula);
                checkboxQuestionFactory.setQuestionBuilder(checkboxQuestionBuilder);
                questions.add(checkboxQuestionFactory.getQuestion());
            } else if (configData.getClass().equals(QuestionEsseConfigData.class)) {
                esseQuestionBuilder.setConfigData(configData);
                esseQuestionBuilder.setGlobalPreambula(globalPreambula);
                esseQuestionFactory.setQuestionBuilder(esseQuestionBuilder);
                questions.add(esseQuestionFactory.getQuestion());
            } else if (configData.getClass().equals(QuestionYesNoConfigData.class)) {
                yesNoQuestionBuilder.setConfigData(configData);
                yesNoQuestionBuilder.setGlobalPreambula(globalPreambula);
                yesNoQuestionFactory.setQuestionBuilder(yesNoQuestionBuilder);
                questions.add(yesNoQuestionFactory.getQuestion());
            } else if (configData.getClass().equals(QuestionRadioButtonConfigData.class)) {
                radioButtonQuestionBuilder.setConfigData(configData);
                radioButtonQuestionBuilder.setGlobalPreambula(globalPreambula);
                radioButtonQuestionFactory.setQuestionBuilder(radioButtonQuestionBuilder);
                questions.add(radioButtonQuestionFactory.getQuestion());
            } else{
                System.err.println("222!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }
        variant.setQuestionList(questions);
        return variant;
    }
}
