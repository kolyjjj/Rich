/**
 * Created by IntelliJ IDEA.
 * User: koly
 * Date: 2/21/12
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cottage extends Land {
    public Cottage(int landID, double originalPrice, Player owner) {
        super(landID, originalPrice, owner);
        this.constructPrice = originalPrice*2;
        landForm = "1";
    }

}
