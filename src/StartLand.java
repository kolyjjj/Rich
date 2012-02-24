/**
 * Created by IntelliJ IDEA.
 * User: koly
 * Date: 2/21/12
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class StartLand extends Land {
    public StartLand(int landPositin, double price, Player owner) {
        super(landPositin, price, owner);
        this.landForm = "S";
    }
}
