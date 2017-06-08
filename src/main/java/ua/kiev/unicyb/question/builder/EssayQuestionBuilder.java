package ua.kiev.unicyb.question.builder;

import ua.kiev.unicyb.question.answers.QuestionAnswers;
import ua.kiev.unicyb.question.format.FormatSettings;
import ua.kiev.unicyb.question.format.FormatType;
import ua.kiev.unicyb.question.EssayQuestion;

/**
 * @Author Denys Storozhenko.
 */
public class EssayQuestionBuilder extends AbstractQuestionBuilder {

	@Override
	public EssayQuestion build() {
		EssayQuestion essayQuestion = new EssayQuestion();
		essayQuestion.setQuestion(globalPreamble +" "+configData.getPreamble());
		FormatSettings formatSettings = new FormatSettings();
		formatSettings.setType(FormatType.valueOf(configData.getFormatElements().getFormatStrategy().name()));
		formatSettings.setCount(configData.getFormatElements().getCount());
		essayQuestion.setFormatSettings(formatSettings);
		essayQuestion.setEstimation(estimation);
		essayQuestion.setHint(configData.getHint());

		QuestionAnswers questionAnswers = new QuestionAnswers();
		questionAnswers.setAnswers(new String[]{});
		essayQuestion.setCorrectAnswers(questionAnswers);

		essayQuestion.setVariantsOfAnswers(new String[]{});

		return essayQuestion;
	}
}
