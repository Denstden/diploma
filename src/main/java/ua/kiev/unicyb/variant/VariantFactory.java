package ua.kiev.unicyb.variant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ua.kiev.unicyb.parser.config.question.QuestionConfigData;
import ua.kiev.unicyb.parser.config.question.QuestionData;
import ua.kiev.unicyb.parser.config.question.question_types.AbstractQuestionConfigData;
import ua.kiev.unicyb.parser.config.question.question_types.QuestionCheckboxConfigData;
import ua.kiev.unicyb.parser.config.question.question_types.QuestionEssayConfigData;
import ua.kiev.unicyb.parser.config.question.question_types.QuestionRadioButtonConfigData;
import ua.kiev.unicyb.parser.config.question.question_types.QuestionYesNoConfigData;
import ua.kiev.unicyb.parser.config.variant.VariantConfig;
import ua.kiev.unicyb.question.AbstractQuestion;
import ua.kiev.unicyb.question.builder.AbstractQuestionBuilder;
import ua.kiev.unicyb.question.factory.AbstractQuestionFactory;
import ua.kiev.unicyb.question.factory.CheckboxQuestionFactory;
import ua.kiev.unicyb.question.factory.EssayQuestionFactory;
import ua.kiev.unicyb.question.factory.RadioButtonQuestionFactory;
import ua.kiev.unicyb.question.factory.YesNoQuestionFactory;

public class VariantFactory {
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

	public Variant getVariant(Integer number) {
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
		return variant;
	}

	private AbstractQuestion handleQuestionData(QuestionData questionData) {
		AbstractQuestion question = null;
		QuestionConfigData questionConfigData;
		String globalPreambula;
		List<AbstractQuestionConfigData> configDatas;
		AbstractQuestionConfigData configData;
		CheckboxQuestionFactory checkboxQuestionFactory = (CheckboxQuestionFactory) questionFactories.get(0);
		EssayQuestionFactory essayQuestionFactory = (EssayQuestionFactory) questionFactories.get(1);
		RadioButtonQuestionFactory radioButtonQuestionFactory = (RadioButtonQuestionFactory) questionFactories.get(2);
		YesNoQuestionFactory yesNoQuestionFactory = (YesNoQuestionFactory) questionFactories.get(3);
		if (questionData.getQuestionConfigs().size() > 1) {
			System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
			//   return buildCombinedQuestion(questionData.getQuestionConfigs());
		}
		questionConfigData = questionData.getQuestionConfigs().get(0).getQuestionConfigData();
		globalPreambula = questionConfigData.getGlobalPreamble();
		configDatas = questionConfigData.getQuestionConfigDatas();
		configData = configDatas.get(random.nextInt(configDatas.size()));
		if (configData.getClass().equals(QuestionCheckboxConfigData.class)) {
			initBuilder(checkboxQuestionFactory.getQuestionBuilder(), configData, globalPreambula);
			question = checkboxQuestionFactory.getQuestion();
		} else if (configData.getClass().equals(QuestionEssayConfigData.class)) {
			initBuilder(essayQuestionFactory.getQuestionBuilder(), configData, globalPreambula);
			question = essayQuestionFactory.getQuestion();
		} else if (configData.getClass().equals(QuestionYesNoConfigData.class)) {
			initBuilder(yesNoQuestionFactory.getQuestionBuilder(), configData, globalPreambula);
			question = yesNoQuestionFactory.getQuestion();
		} else if (configData.getClass().equals(QuestionRadioButtonConfigData.class)) {
			initBuilder(radioButtonQuestionFactory.getQuestionBuilder(), configData, globalPreambula);
			question = radioButtonQuestionFactory.getQuestion();
		} else {
			System.err.println("222!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
		return question;
	}

	private void initBuilder(AbstractQuestionBuilder builder, AbstractQuestionConfigData configData, String preamble) {
		builder.setConfigData(configData);
		builder.setGlobalPreamble(preamble);
	}
}
