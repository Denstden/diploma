package question.builder;

import question.format.FormatSettings;
import question.format.FormatType;
import question.YesNoQuestion;

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

		yesNoQuestion.setAnswers(new String[]{"Так", "Ні"});
		return yesNoQuestion;
	}
}
