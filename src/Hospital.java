/**
 * Created by IntelliJ IDEA.
 * User: koly
 * Date: 2/21/12
 * Time: 7:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hospital extends Land {
    public Hospital(int landID, double price, Player owner) {
        super(landID, price, owner);
        this.landForm = "H";
    }
}
