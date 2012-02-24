/**
 * Created by IntelliJ IDEA.
 * User: koly
 * Date: 2/21/12
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class GiftHouse extends Land {
    public GiftHouse(int landID, double price, Player owner) {
        super(landID, price, owner);
        this.landForm = "G";
    }
}
