package new_builder;

import format.FormatSettings;
import format.FormatType;
import new_parser2.config.question.QuestionConfig;
import new_parser2.config.question.QuestionConfigData;
import new_parser2.config.question.QuestionData;
import new_parser2.config.question.question_types.AbstractQuestionConfigData;
import new_parser2.config.question.question_types.QuestionCheckboxConfigData;
import new_parser2.config.question.question_types.QuestionEsseConfigData;
import question.AbstractQuestion;
import question.QuestionType1;
import question.QuestionType3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by storo on 5/18/2016.
 */
public class QuestionBuilder {
    private QuestionData questionData;
    private Random random = new Random();

    public QuestionData getQuestionData() {
        return questionData;
    }

    public void setQuestionData(QuestionData questionData) {
        this.questionData = questionData;
    }

    public AbstractQuestion build(){
        if (questionData.getQuestionConfigs().size()>1){
            return buildCombinedQuestion(questionData.getQuestionConfigs());
        }

        assert (questionData.getQuestionConfigs().size() == 1);

        QuestionConfigData questionConfigData = questionData.getQuestionConfigs().get(0).getQuestionConfigData();
        String globalPreambula = questionConfigData.getGlobalPreambula();

        List<AbstractQuestionConfigData> configDatas = questionConfigData.getQuestionConfigDatas();
        AbstractQuestionConfigData configData = configDatas.get(random.nextInt(configDatas.size()));

        if (configData.getClass().equals(QuestionCheckboxConfigData.class)){
            return buildCheckboxQuestion((QuestionCheckboxConfigData)configData, globalPreambula);
        }
        if (configData.getClass().equals(QuestionEsseConfigData.class)){
            return buildEsseQuestion((QuestionEsseConfigData)configData, globalPreambula);
        }


        return null;
    }

    private QuestionType1 buildCheckboxQuestion(QuestionCheckboxConfigData configData, String globalPreambula){
        QuestionType1 questionType1 = new QuestionType1();
        questionType1.setQuestion(globalPreambula+" "+configData.getPreambula());

        FormatSettings formatSettings = new FormatSettings();
        formatSettings.setType(FormatType.valueOf(configData.getFormatElements().getFormatStrategy().name()));
        formatSettings.setCount(configData.getFormatElements().getCount());
        questionType1.setFormatSettings(formatSettings);

        questionType1.setAnswers(buildCheckboxAnswers(configData));
        return questionType1;
    }

    private String[] buildCheckboxAnswers(QuestionCheckboxConfigData configData){
        Integer count = configData.getCountAnswers();
        String[] answers = new String[count];

        List<String> answersList = new ArrayList<>();

        List<String> correctAnswers = configData.getCorrectAnswers();
        Collections.shuffle(correctAnswers);
        for (int i=0;i<configData.getCountCorrectAnswers();i++){
            answersList.add(correctAnswers.get(i%correctAnswers.size()));
        }

        List<String> inCorrectAnswers = configData.getIncorrectAnswers();
        Collections.shuffle(inCorrectAnswers);
        for (int i=0;i<configData.getCountIncorrectAnswers();i++){
            answersList.add(inCorrectAnswers.get(i%inCorrectAnswers.size()));
        }

        Collections.shuffle(answersList);
        return answersList.toArray(answers);
    }


    private QuestionType3 buildEsseQuestion(QuestionEsseConfigData configData, String globalPreambula) {
        QuestionType3 questionType3 = new QuestionType3();
        questionType3.setQuestion(globalPreambula+" "+configData.getPreambula());
        FormatSettings formatSettings = new FormatSettings();
        formatSettings.setType(FormatType.valueOf(configData.getFormatElements().getFormatStrategy().name()));
        formatSettings.setCount(configData.getFormatElements().getCount());
        questionType3.setFormatSettings(formatSettings);
        return questionType3;
    }

    private AbstractQuestion buildCombinedQuestion(List<QuestionConfig> questionConfigs) {
        return null;
    }


}
