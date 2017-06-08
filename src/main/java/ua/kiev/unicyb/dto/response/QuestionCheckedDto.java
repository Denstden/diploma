package ua.kiev.unicyb.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by storo on 6/7/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionCheckedDto {
    private String name;
    private Double mark = 0d;
    private Double maxMark;
    private Map<String, Boolean> answers = new LinkedHashMap<>();
    private String hint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Double getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(Double maxMark) {
        this.maxMark = maxMark;
    }

    public Map<String, Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, Boolean> answers) {
        this.answers = answers;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
