
public class Tool {

    protected int points;
    protected String name;
    protected String symbol;

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public String getSymbol(){
        return symbol;
    }

    public boolean isEmpty() {
        return name.equals("empty");
    }
}
