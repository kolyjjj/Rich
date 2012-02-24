/**
 * Created by IntelliJ IDEA.
 * User: koly
 * Date: 2/21/12
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmptyLand extends Land {

    public EmptyLand(int landPosition, double price, Player owner){
        super(landPosition, price, owner);
        constructPrice = price;
        landForm = "0";


    }

}
