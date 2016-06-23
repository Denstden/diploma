package ua.kiev.unicyb.question.builder;

import ua.kiev.unicyb.parser.config.question.question_types.QuestionYesNoConfigData;
import ua.kiev.unicyb.question.answers.QuestionAnswers;
import ua.kiev.unicyb.question.format.FormatSettings;
import ua.kiev.unicyb.question.format.FormatType;
import ua.kiev.unicyb.question.YesNoQuestion;

/**
 * @Author Denys Storozhenko.
 */
public class YesNoQuestionBuilder extends AbstractQuestionBuilder {
	@Override
	public YesNoQuestion build() {
		YesNoQuestion yesNoQuestion = new YesNoQuestion();
		yesNoQuestion.setQuestion(globalPreamble +" "+configData.getPreamble());

		FormatSettings formatSettings = new FormatSettings();
		formatSettings.setType(FormatType.valueOf(configData.getFormatElements().getFormatStrategy().name()));
		formatSettings.setCount(configData.getFormatElements().getCount());
		yesNoQuestion.setFormatSettings(formatSettings);

		yesNoQuestion.setVariantsOfAnswers(new String[]{"Так", "Ні"});
		QuestionAnswers questionAnswers = new QuestionAnswers();
		String[] correctAnswer = new String[1];
		correctAnswer[0] = ((QuestionYesNoConfigData)configData).getAnswer();
		questionAnswers.setQuestionAnswers(correctAnswer);
		yesNoQuestion.setCorrectAnswers(questionAnswers);
		return yesNoQuestion;
	}
}
