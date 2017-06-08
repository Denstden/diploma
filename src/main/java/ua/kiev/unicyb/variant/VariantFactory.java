package ua.kiev.unicyb.variant;

import ua.kiev.unicyb.exception.UnsupportedQuestionTypeException;
import ua.kiev.unicyb.parser.config.question.QuestionConfig;
import ua.kiev.unicyb.parser.config.question.QuestionConfigData;
import ua.kiev.unicyb.parser.config.question.QuestionData;
import ua.kiev.unicyb.parser.config.question.question_types.*;
import ua.kiev.unicyb.parser.config.variant.Estimation;
import ua.kiev.unicyb.parser.config.variant.VariantConfig;
import ua.kiev.unicyb.question.AbstractQuestion;
import ua.kiev.unicyb.question.builder.AbstractQuestionBuilder;
import ua.kiev.unicyb.question.factory.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VariantFactory {
    private static final int MAX_RETRIES_TO_CHECK_HASHTAG = 10;

    private VariantConfig config;

    private final List<AbstractQuestionFactory> questionFactories = new ArrayList<>();

    private final Random random = new Random();

    public VariantFactory() {
        questionFactories.add(new CheckboxQuestionFactory());
        questionFactories.add(new EssayQuestionFactory());
        questionFactories.add(new RadioButtonQuestionFactory());
        questionFactories.add(new YesNoQuestionFactory());
    }

    public VariantConfig getConfig() {
        return config;
    }

    public void setConfig(VariantConfig config) {
        this.config = config;
    }

    public Variant getVariant(Integer number) throws UnsupportedQuestionTypeException {
        Variant variant = new Variant();
        variant.setName("" + number);
        String header = "";
        for (String line : config.getHeader()) {
            header += line + "\n";
        }
        variant.setPreamble(header);
        List<AbstractQuestion> questions = new ArrayList<>();
        AbstractQuestion question;

        for (QuestionData questionData : config.getQuestions()) {
            question = handleQuestionData(questionData);
            if (question != null) {
                questions.add(question);
            }
        }
        variant.setQuestionList(questions);
        variant.setMark(config.getPoints());
        return variant;
    }

    private AbstractQuestion handleQuestionData(QuestionData questionData) throws UnsupportedQuestionTypeException {
        AbstractQuestion question = null;
        QuestionConfigData questionConfigData;
        String globalPreamble;
        List<AbstractQuestionConfigData> configDatas;
        AbstractQuestionConfigData configData;
        Estimation estimation;
        if (questionData.getQuestionConfigs().size() > 1) {
            return handleCombinedQuestion(questionData);
        }
        int retries = 0;
        List<String> hashTags = questionData.getHashtags();
        
        do {
            questionConfigData = questionData.getQuestionConfigs().get(0).getQuestionConfigData();
            globalPreamble = questionConfigData.getGlobalPreamble();
            configDatas = questionConfigData.getQuestionConfigDatas();
            configData = configDatas.get(random.nextInt(configDatas.size()));
        }
        while (retries++ < MAX_RETRIES_TO_CHECK_HASHTAG && hashTags != null && !hashTags.contains(configData.getHashTag()));

        estimation = questionData.getEstimation();
        question = handleConfigData(globalPreamble, configData, estimation);
        return question;
    }

    private AbstractQuestion handleCombinedQuestion(QuestionData questionData) throws UnsupportedQuestionTypeException {
        QuestionConfigData questionConfigData;
        String globalPreamble;
        AbstractQuestionConfigData configData;
        Estimation estimation;
        List<AbstractQuestion> questions = new ArrayList<>();
        for (int i = 0; i < questionData.getQuestionConfigs().size(); i++) {
            QuestionConfig questionConfig = questionData.getQuestionConfigs().get(i);
            for (int j = 0; j < questionConfig.getCountOfQuestions(); j++) {
                int retries = 0;
                List<String> hashTags = questionData.getHashtags();
                do {
                    Integer rnd = random.nextInt(questionConfig.getCountOfQuestions());
                    questionConfigData = questionConfig.getQuestionConfigData();
                    globalPreamble = questionConfigData.getGlobalPreamble();
                    configData = questionConfigData.getQuestionConfigDatas().get(rnd);
                }
                while (retries++ < MAX_RETRIES_TO_CHECK_HASHTAG && hashTags != null && !hashTags.contains(configData.getHashTag()));

                estimation = questionData.getEstimation();
                questions.add(handleConfigData(globalPreamble, configData, estimation));
            }
        }
        Collections.shuffle(questions);
        return questions.get(0);
    }

    private AbstractQuestion handleConfigData(String globalPreamble, AbstractQuestionConfigData configData,
                                              Estimation estimation) throws UnsupportedQuestionTypeException {
        CheckboxQuestionFactory checkboxQuestionFactory = (CheckboxQuestionFactory) questionFactories.get(0);
        EssayQuestionFactory essayQuestionFactory = (EssayQuestionFactory) questionFactories.get(1);
        RadioButtonQuestionFactory radioButtonQuestionFactory = (RadioButtonQuestionFactory) questionFactories.get(2);
        YesNoQuestionFactory yesNoQuestionFactory = (YesNoQuestionFactory) questionFactories.get(3);
        AbstractQuestion question = null;
        if (configData.getClass().equals(QuestionCheckboxConfigData.class)) {
            initBuilder(checkboxQuestionFactory.getQuestionBuilder(), configData, globalPreamble, estimation);
            question = checkboxQuestionFactory.getQuestion();

        } else if (configData.getClass().equals(QuestionEssayConfigData.class)) {
            initBuilder(essayQuestionFactory.getQuestionBuilder(), configData, globalPreamble, estimation);
            question = essayQuestionFactory.getQuestion();

        } else if (configData.getClass().equals(QuestionYesNoConfigData.class)) {
            initBuilder(yesNoQuestionFactory.getQuestionBuilder(), configData, globalPreamble, estimation);
            question = yesNoQuestionFactory.getQuestion();
        } else if (configData.getClass().equals(QuestionRadioButtonConfigData.class)) {
            initBuilder(radioButtonQuestionFactory.getQuestionBuilder(), configData, globalPreamble, estimation);
            question = radioButtonQuestionFactory.getQuestion();
        } else {
            throw new UnsupportedQuestionTypeException();
        }
        return question;
    }

    private void initBuilder(AbstractQuestionBuilder builder, AbstractQuestionConfigData configData, String preamble,
                             Estimation estimation) {
        builder.setConfigData(configData);
        builder.setGlobalPreamble(preamble);
        builder.setEstimation(estimation);
    }
}
