/**
 * Created by IntelliJ IDEA.
 * User: koly
 * Date: 2/21/12
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mine extends Land {
    private int points;
    public Mine(int landID, double price, Player owner) {
        super(landID, price, owner);
        this.landForm = "$";
        points = 0;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public int getPoints(){
        return points;
    }
}
