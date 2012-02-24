/**
 * Created by IntelliJ IDEA.
 * User: koly
 * Date: 2/21/12
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ToolHouse extends Land {
    public ToolHouse(int landID, double price, Player owner) {
        super(landID, price, owner);
        this.landForm = "T";
    }
}
