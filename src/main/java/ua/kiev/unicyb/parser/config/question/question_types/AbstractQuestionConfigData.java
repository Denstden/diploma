package ua.kiev.unicyb.parser.config.question.question_types;

import ua.kiev.unicyb.parser.config.question.FormatElements;

public abstract class AbstractQuestionConfigData {
	FormatElements formatElements;

	String preamble = "";

	String hashTag;

	String hint;

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

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
}
