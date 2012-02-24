/**
 * Created by IntelliJ IDEA.
 * User: koly
 * Date: 2/21/12
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class MagicLand extends Land {
    public MagicLand(int landID, double price, Player owner) {
        super(landID, price, owner);
        this.landForm = "M";
    }
}
