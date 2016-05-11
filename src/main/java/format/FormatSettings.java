package format;

public class FormatSettings {
    private FormatType type;
    private int count;
    public void setType(FormatType t){
        this.type=t;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FormatType getType(){
        return type;
    }

    public int getCount(){
        return count;
    }

}