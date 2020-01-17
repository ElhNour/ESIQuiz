package sample.P1;

public class Proposition {
    private String propsition;
    private Boolean val_verite;

    public void setPropsition(String propsition) {
        this.propsition = propsition;
    }

    public String getPropsition() {
        return propsition;
    }

    public void setVal_verite(Boolean val_verite) {
        this.val_verite = val_verite;
    }

    public Boolean getVal_verite() {
        return val_verite;
    }
    public Proposition(String var1,Boolean var2){
        this.propsition=var1;
        this.val_verite=var2;
    }
}
