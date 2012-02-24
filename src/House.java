/**
 * Created by IntelliJ IDEA.
 * User: koly
 * Date: 2/21/12
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class House extends Land {
    public House(int landPosition, double originalPrice, Player owner) {
        super(landPosition, originalPrice, owner);
        this.constructPrice = originalPrice*3;
        this.landForm = "2";
    }
}
