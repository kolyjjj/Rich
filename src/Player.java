import java.util.ArrayList;


public class Player {
    private int currentPosition;
    private double money;
    private ArrayList<Land> lands;
    private String symbol;
    private String name;
    private String color;
    private int points;
    private int daysInJail;
    private int godDays;
    private ArrayList<Tool> tools;
    private int hospitalDays;
    private boolean covered;

    public Player() {
        lands = new ArrayList<Land>();
        tools = new ArrayList<Tool>();
    }

    public Player(double money, String symbol, String name, String color) {
        this.money = money;
        this.symbol = symbol;
        this.name = name;
        this.color = color;
        this.points = 0;
        daysInJail = 0;
        godDays = 0;
        hospitalDays = 0;
        covered = false;
        lands = new ArrayList<Land>();
        tools = new ArrayList<Tool>();
//        tools.add(new Bomb());
//        tools.add(new Block());
//        tools.add(new Robot());
    }


    public int getCurrentPosition() {
        return currentPosition;
    }

    public void proceed(int steps) {
        currentPosition += steps;
        if (currentPosition > 69) {
            currentPosition -= 70;
        }
    }

    public int roll() {
        int randomNum = (int) (Math.random() * 6 + 1);
        proceed(randomNum);
        return randomNum;
    }

    public void setCurrentPosition(int newPosition) {
        currentPosition = newPosition;
    }

    public int buyLand(Land emptyLand) {
        money -= emptyLand.getConstructPrice();
        int index = lands.size();
        lands.add(emptyLand);
        return index;
    }

    public Land getLand(int landIndex) {
        return lands.get(landIndex);
    }

    public double getMoney() {
        return money;
    }

    public Land updateLand(Land land) {

        int index = getIndex(land);
        Land updateLand = land;
        if (land.isEmptyLand()) {
            lands.set(index, new Cottage(land.getLandID(), land.getOriginalPrice(), land.getOwner()));
            updateLand = new Cottage(land.getLandID(), land.getOriginalPrice(), land.getOwner());
        } else if (land.isCottage()) {
            lands.set(index, new House(land.getLandID(), land.getOriginalPrice(), land.getOwner()));
            updateLand = new House(land.getLandID(), land.getOriginalPrice(), land.getOwner());
        } else if (land.isHouse()) {
            lands.set(index, new Villa(land.getLandID(), land.getOriginalPrice(), land.getOwner()));
            updateLand = new Villa(land.getLandID(), land.getOriginalPrice(), land.getOwner());
        }
        money -= land.getOriginalPrice();
        return updateLand;
    }

    public int getIndex(Land land) {
        for (int i = 0; i < lands.size(); i++) {
            if (lands.get(i).getLandID() == land.getLandID()) {
                return i;
            }
        }
        return -1;
    }

    public Land sellLand(Land land) {
        int index = getIndex(land);
        if (index == -1)
            return null;
        lands.remove(index);
        money += land.getConstructPrice() * 2;
        return new EmptyLand(land.getLandID(), land.getOriginalPrice(), new Player(0, "", "Game", ""));
    }

    public void payPassFee(double passFee) {
        money -= passFee;

    }

    public void getPassFee(double passFee) {
        money += passFee;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Player p) {
        return this.name.equals(p.getName());
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getJailDays() {
        return daysInJail;
    }

    public void setDaysInJails(int jailDays) {
        daysInJail = jailDays;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void getGod() {
        godDays = 5;
    }

    public int getGodDays() {
        return godDays;
    }

    public void setGodDays(int days) {
        godDays = days;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean buyTool(Tool tool) {
        tools.add(tool);
        points -= tool.getPoints();
        return true;
    }

    public int getToolsNum() {
        return tools.size();
    }

    public int getLandNum(String land) {
        int num = 0;
        for (Land l : lands){
            if (l.getLandForm().equals(land)){
                num++;
            }
        }
        return num;
    }

    public int getToolNum(String tool) {
        int i = 0;
        for (Tool t : tools){
            if (t.getName().equals(tool)){
                i++;
            }
        }
        return i;
    }

    public Tool getTool(String t) {
        int i = 0;
        for (; i < tools.size(); i++) {
            if (tools.get(i).getName().equals(t)) {
                return tools.remove(i);
            }
        }
        return new EmptyTool();
    }

    public int getHospitalDays() {
        return hospitalDays;
    }

    public boolean hasTool(String t) {
        for (Tool tool : tools) {
            if (tool.getName().equals(t)) {
                return true;
            }
        }
        return false;
    }

    public void setHospitalDays(int hospitalDays) {
        this.hospitalDays = hospitalDays;
    }


    public boolean sellTool(int toolIndex) {
        if (toolIndex < 0 || toolIndex > tools.size() - 1) {
            return false;
        }
        points += tools.remove(toolIndex).getPoints();
        return true;
    }

    public boolean hasNoTool() {
        return tools.isEmpty();
    }

    public void setMoney(int val) {
        money = val;
    }

    public String getColor() {
        return color;
    }

    public boolean isEmpty() {
        return name.equals("Game");
    }

    public void setCovered(boolean b) {
        covered = b;
    }

    public boolean getCovered() {
        return covered;
    }
}
