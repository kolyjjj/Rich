/**
 * Created by IntelliJ IDEA.
 * User: koly
 * Date: 2/21/12
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Villa extends Land {
    public Villa(int landID, double originalPrice, Player owner) {
        super(landID, originalPrice, owner);
        this.constructPrice = originalPrice*4;
        this.landForm = "3";
    }
}
