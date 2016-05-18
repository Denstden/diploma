package new_parser2.config.test;

import java.util.List;

public class TestConfig {
    private String resultSource;
    private Integer countOfVariants;
    private List<String> header;

    public String getResultSource() {
        return resultSource;
    }

    public void setResultSource(String resultSource) {
        this.resultSource = resultSource;
    }

    public Integer getCountOfVariants() {
        return countOfVariants;
    }

    public void setCountOfVariants(Integer countOfVariants) {
        this.countOfVariants = countOfVariants;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "TestConfig{" +
                "resultSource='" + resultSource + '\'' +
                ", countOfVariants=" + countOfVariants +
                ", header=" + header +
                '}';
    }
}
