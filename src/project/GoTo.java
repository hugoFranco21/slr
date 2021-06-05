package project;

public class GoTo {
    private Integer from;
    private String to;

    public GoTo(){}

    public GoTo(Integer from, String to){
        this.from = from;
        this.to = to;
    }

    public Integer getFrom(){
        return this.from;
    }

    public String getTo(){
        return this.to;
    }
}
