package ua.kiev.unicyb.parser.config.question;

import java.util.List;

import ua.kiev.unicyb.parser.config.question.question_types.AbstractQuestionConfigData;

public class QuestionConfigData {
	private List<AbstractQuestionConfigData> questionConfigDatas;

	private String globalPreamble = "";

	public List<AbstractQuestionConfigData> getQuestionConfigDatas() {
		return questionConfigDatas;
	}

	public void setQuestionConfigDatas(List<AbstractQuestionConfigData> questionConfigDatas) {
		this.questionConfigDatas = questionConfigDatas;
	}

	public String getGlobalPreamble() {
		return globalPreamble;
	}

	public void setGlobalPreamble(String globalPreamble) {
		this.globalPreamble = globalPreamble;
	}

	@Override
	public String toString() {
		return "QuestionConfigData{" +
				"questionConfigDatas=" + questionConfigDatas +
				", globalPreamble='" + globalPreamble + '\'' +
				'}';
	}
}
