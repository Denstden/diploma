package new_parser2.config.variant;

/**
 * Created by storo on 5/18/2016.
 */
public class Estimation {
    private Double mark;
    private EstimationStrategy strategy;

    public enum EstimationStrategy{
        ALL_NOTHING,
        EVENLY
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public EstimationStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(EstimationStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return "Estimation{" +
                "mark=" + mark +
                ", strategy=" + strategy +
                '}';
    }
}
