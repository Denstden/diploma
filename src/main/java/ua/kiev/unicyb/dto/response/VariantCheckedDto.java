package ua.kiev.unicyb.dto.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by storo on 6/7/2017.
 */
public class VariantCheckedDto {
    private List<QuestionCheckedDto> questions = new ArrayList<>();
    private Double mark;
    private Double maxMark;

    public List<QuestionCheckedDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionCheckedDto> questions) {
        this.questions = questions;
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
}
