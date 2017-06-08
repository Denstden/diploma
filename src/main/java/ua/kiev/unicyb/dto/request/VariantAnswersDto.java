package ua.kiev.unicyb.dto.request;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by storo on 6/1/2017.
 */
public class VariantAnswersDto {
    private String variantName;
    private Map<String, List<String>> answers = new LinkedHashMap<>();

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public Map<String, List<String>> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, List<String>> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "VariantAnswersDto{" +
                "variantName='" + variantName + '\'' +
                ", answers=" + answers +
                '}';
    }
}
