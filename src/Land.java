
public class Land {
    protected double constructPrice;
    protected String landForm;
    private int landID;
    protected double originalPrice;
    protected Player owner;

    public Land(){
        
    }
    
    public Land(int landPosition, double price, Player owner){
        landID = landPosition;
        originalPrice = price;
        this.owner = owner;
    }
    
    public double getConstructPrice() {
        return constructPrice;
    }

    public boolean equals(Land otherLand){
        return landForm.equals(otherLand.getLandForm()) && landID == otherLand.getLandID();
    }

    public String getLandForm() {
        return landForm;
    }

    public int getLandID() {
        return landID;
    }



    public double getOriginalPrice() {
        return originalPrice;
    }

    public boolean isCottage() {
        return landForm.equals("1");
    }

    public boolean isHouse() {
        return landForm.equals("2");
    }

    public Player getOwner() {
        return owner;
    }

    public void setTwoPrice(double landPrice) {
        this.originalPrice = landPrice;
        this.constructPrice = landPrice;
    }

    public boolean hasNoOwner() {
        return owner.equals(new Player(0,"","Game", ""));
    }

    public boolean isEmptyLand() {
        return landForm.equals("0");
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isFinal() {
        return this.landForm.equals("3");
    }

    public boolean isGameLand() {
        return owner.equals(new Player(0,"","Game",""));
    }

    public boolean isMine() {
        return landForm.equals("$");
    }

    public int getPoints() {
        return 0;
    }
    public void setPoints(int points){

    }

    public boolean isJail() {
        return landForm.equals("P");
    }

    public boolean isGiftHouse() {
        return landForm.equals("G");
    }

    public boolean isToolHouse() {
        return landForm.equals("T");
    }

    public boolean isMagicHouse() {
        return landForm.equals("M");
    }

    public boolean isHospital() {
        return landForm.equals("H");
    }
}



