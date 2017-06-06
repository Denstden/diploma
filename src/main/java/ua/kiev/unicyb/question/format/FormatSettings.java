package ua.kiev.unicyb.question.format;

import java.io.Serializable;

public class FormatSettings implements Serializable{
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
