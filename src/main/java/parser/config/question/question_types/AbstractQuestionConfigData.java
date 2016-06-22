package parser.config.question.question_types;

import parser.config.question.FormatElements;

public abstract class AbstractQuestionConfigData {
	protected FormatElements formatElements;

	protected String preamble = "";

	protected String hashTag;

	public FormatElements getFormatElements() {
		return formatElements;
	}

	public void setFormatElements(FormatElements formatElements) {
		this.formatElements = formatElements;
	}

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}

	public String getPreamble() {
		return preamble;
	}

	public void setPreamble(String preamble) {
		this.preamble = preamble;
	}
}
