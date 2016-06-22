package parser.config.question.question_types;

import parser.config.question.FormatElements;

public abstract class AbstractQuestionConfigData {
    protected FormatElements formatElements;
    protected String preambula = "";
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

    public String getPreambula() {
        return preambula;
    }

    public void setPreambula(String preambula) {
        this.preambula = preambula;
    }
}
