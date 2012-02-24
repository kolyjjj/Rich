import org.junit.Test;




import static junit.framework.Assert.assertEquals;

import static junit.framework.Assert.assertTrue;


public class PlayerTest {
    @Test
    public void test_player_proceed_1_step(){
        Player p = new Player();
        int currentPosition = p.getCurrentPosition();
        p.proceed(1);
        assertEquals(p.getCurrentPosition(), currentPosition+1);
    }

    @Test
    public void test_player_proceed_6_step(){
        Player p = new Player();
        int currentPosition = p.getCurrentPosition();
        p.proceed(6);
        assertEquals(p.getCurrentPosition(), currentPosition+6);
    }
    
    @Test
    public void test_player_roll_random(){
        Player p = new Player();
        int currentPosition = p.getCurrentPosition();
        assertEquals(currentPosition + p.roll(), p.getCurrentPosition());
    }

    @Test
    public void test_player_go_back_to_origin(){
        Player p = new Player();
        p.setCurrentPosition(69);
        p.proceed(1);
        assertEquals(p.getCurrentPosition(), 0);
    }

    @Test
    public void test_player_go_further_than_origin(){
        Player p =new Player();
        p.setCurrentPosition(68);
        p.proceed(4);
        assertEquals(p.getCurrentPosition(), 2);
    }
    
    @Test
    public void test_player_buy_an_empty_land_with_200_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land  emptyLand = new EmptyLand(10, 200, new Player(0,"","Game",""));   //construct an empty land in the position 10
        double money = p.getMoney();
        int landIndex = p.buyLand(emptyLand);
        assertTrue(p.getLand(landIndex).equals(emptyLand));
        assertEquals(p.getMoney(), money-200);
    }

    @Test
    public void test_player_buy_an_empty_land_with_300_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land  emptyLand = new EmptyLand(10, 300, new Player(0,"","Game",""));   //construct an empty land in the position 10
        double money = p.getMoney();
        int landIndex = p.buyLand(emptyLand);
        assertTrue(p.getLand(landIndex).equals(emptyLand));
        assertEquals(p.getMoney(), money-300);
    }

    @Test
    public void test_player_buy_an_empty_land_with_500_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land  emptyLand = new EmptyLand(10, 500, new Player(0,"","Game",""));   //construct an empty land in the position 10
        double money = p.getMoney();
        int landIndex = p.buyLand(emptyLand);
        assertTrue(p.getLand(landIndex).equals(emptyLand));
        assertEquals(p.getMoney(), money-500);
    }

    @Test
    public void test_player_update_an_empty_land_with_200_dollars(){
        Player p =new Player(10000, "S", "孙美美", "");
        Land emptyLand = new EmptyLand(10, 200, new Player(0,"","Game",""));  //update the land in the position 10 and the land price is 200
        p.buyLand(emptyLand);
        double money = p.getMoney();
        Land updatedLand = p.updateLand(emptyLand);
        assertTrue(updatedLand.equals(new Cottage(10, 200,p)));
        assertEquals(p.getMoney(), money-200);
        assertEquals(updatedLand.getConstructPrice(), emptyLand.getConstructPrice()+200);

    }

    @Test
    public void test_player_update_an_cottage_land_with_200_dollars(){
        Player p =new Player(10000, "S", "孙美美", "");
        Land cottageLand = new Cottage(10, 200,p);  //update the land in the position 10 and the land price is 200
        p.buyLand(cottageLand);
        double money = p.getMoney();
        Land updatedLand = p.updateLand(cottageLand);
        assertTrue(updatedLand.equals(new House(10, 200,p)));
        assertEquals(p.getMoney(), money-200);
        assertEquals(updatedLand.getConstructPrice(), cottageLand.getConstructPrice()+200);

    }

    @Test
    public void test_player_update_an_house_land_with_200_dollars(){
        Player p =new Player(10000, "S", "孙美美", "");
        Land cottageLand = new House(10, 200,p);  //update the land in the position 10 and the land price is 200
        p.buyLand(cottageLand);
        double money = p.getMoney();
        Land updatedLand = p.updateLand(cottageLand);
        assertTrue(updatedLand.equals(new Villa(10, 200,p)));
        assertEquals(p.getMoney(), money-200);
        assertEquals(updatedLand.getConstructPrice(), cottageLand.getConstructPrice()+200);

    }

    @Test
    public void test_player_update_an_empty_land_with_300_dollars(){
        Player p =new Player(10000, "S", "孙美美", "");
        Land emptyLand = new EmptyLand(10, 300, new Player(0,"","Game",""));  //update the land in the position 10 and the land price is 200
        p.buyLand(emptyLand);
        double money = p.getMoney();
        Land updatedLand = p.updateLand(emptyLand);
        assertTrue(updatedLand.equals(new Cottage(10, 300,p)));
        assertEquals(p.getMoney(), money - 300);
        assertEquals(updatedLand.getConstructPrice(), emptyLand.getConstructPrice()+300);

    }

    @Test
    public void test_player_update_an_cottage_land_with_300_dollars(){
        Player p =new Player(10000, "S", "孙美美", "");
        Land cottageLand = new Cottage(10,  300,p);  //update the land in the position 10 and the land price is 300
        p.buyLand(cottageLand);
        double money = p.getMoney();
        Land updatedLand = p.updateLand(cottageLand);
        assertTrue(updatedLand.equals(new House(10, 300,p)));
        assertEquals(p.getMoney(), money - 300);
        assertEquals(updatedLand.getConstructPrice(), cottageLand.getConstructPrice()+300);

    }

    @Test
    public void test_player_update_an_empty_land_with_500_dollars(){
        Player p =new Player(10000, "S", "孙美美", "");
        Land emptyLand = new EmptyLand(10, 500, new Player(0,"","Game",""));  //update the land in the position 10 and the land price is 200
        p.buyLand(emptyLand);
        double money = p.getMoney();
        Land updatedLand = p.updateLand(emptyLand);
        assertTrue(updatedLand.equals(new Cottage(10,  500,p)));
        assertEquals(p.getMoney(), money-500);
        assertEquals(updatedLand.getConstructPrice(), emptyLand.getConstructPrice()+500);

    }

    @Test
    public void test_player_update_an_cottage_land_with_500_dollars(){
        Player p =new Player(10000, "S", "孙美美", "");
        Land cottageLand = new Cottage(10,  500, p);  //update the land in the position 10 and the land price is 500
        p.buyLand(cottageLand);
        double money = p.getMoney();
        Land updatedLand = p.updateLand(cottageLand);
        assertTrue(updatedLand.equals(new House(10,  500, p)));
        assertEquals(p.getMoney(), money-500);
        assertEquals(updatedLand.getConstructPrice(), cottageLand.getConstructPrice()+500);

    }

    @Test
    public void test_player_update_an_house_land_with_500_dollars(){
        Player p =new Player(10000, "S", "孙美美", "");
        Land cottageLand = new House(10, 500, p);  //update the land in the position 10 and the land price is 500
        p.buyLand(cottageLand);
        double money = p.getMoney();
        Land updatedLand = p.updateLand(cottageLand);
        assertTrue(updatedLand.equals(new Villa(10, 500, p)));
        assertEquals(p.getMoney(), money-500);
        assertEquals(updatedLand.getConstructPrice(), cottageLand.getConstructPrice()+500);
    }
    
    @Test
    public void test_player_sell_an_empty_land_with_400_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land emptyLand = new EmptyLand(10, 200, p);
        p.buyLand(emptyLand);
        double money = p.getMoney();
        Land soldLand = p.sellLand(emptyLand);
        assertTrue(soldLand.equals(emptyLand));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money+400);
    }

    @Test
    public void test_player_sell_an_cottage_land_with_800_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land cottage = new Cottage(10, 200,p);
        p.buyLand(cottage);
        double money = p.getMoney();
        Land soldLand = p.sellLand(cottage);
        assertTrue(soldLand.equals(new EmptyLand(10, 200, new Player(0,"","Game",""))));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money+800);
    }

    @Test
    public void test_player_sell_an_house_land_with_1200_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land land = new House(10,  200,p);
        p.buyLand(land);
        double money = p.getMoney();
        Land soldLand = p.sellLand(land);
        assertTrue(soldLand.equals(new EmptyLand(10, 200, new Player(0,"","Game",""))));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money+1200);
    }

    @Test
    public void test_player_sell_an_empty_land_with_600_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land emptyLand = new EmptyLand(10, 300, p);
        p.buyLand(emptyLand);
        double money = p.getMoney();
        Land soldLand = p.sellLand(emptyLand);
        assertTrue(soldLand.equals(emptyLand));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money+600);
    }

    @Test
    public void test_player_sell_an_cottage_land_with_1200_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land cottage = new Cottage(10, 300,p);
        p.buyLand(cottage);
        double money = p.getMoney();
        Land soldLand = p.sellLand(cottage);
        assertTrue(soldLand.equals(new EmptyLand(10, 200, new Player(0,"","Game",""))));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money+1200);
    }

    @Test
    public void test_player_sell_an_house_land_with_1800_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land land = new House(10, 300,p);
        p.buyLand(land);
        double money = p.getMoney();
        Land soldLand = p.sellLand(land);
        assertTrue(soldLand.equals(new EmptyLand(10, 200, new Player(0,"","Game",""))));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money+1800);
    }
    @Test
    public void test_player_sell_an_villa_land_with_2400_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land land = new Villa(10, 300,p);
        p.buyLand(land);
        double money = p.getMoney();
        Land soldLand = p.sellLand(land);
        assertTrue(soldLand.equals(new EmptyLand(10, 200, new Player(0,"","Game",""))));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money+2400);
    }
    @Test
    public void test_player_sell_an_empty_land_with_1000_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land emptyLand = new EmptyLand(10, 500, p);
        p.buyLand(emptyLand);
        double money = p.getMoney();
        Land soldLand = p.sellLand(emptyLand);
        assertTrue(soldLand.equals(emptyLand));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money+1000);
    }

    @Test
    public void test_player_sell_an_cottage_land_with_2000_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land cottage = new Cottage(10, 500,p);
        p.buyLand(cottage);
        double money = p.getMoney();
        Land soldLand = p.sellLand(cottage);
        assertTrue(soldLand.equals(new EmptyLand(10, 200, new Player(0,"","Game",""))));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money+2000);
    }

    @Test
    public void test_player_sell_an_house_land_with_3000_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land land = new House(10, 500,p);
        p.buyLand(land);
        double money = p.getMoney();
        Land soldLand = p.sellLand(land);
        assertTrue(soldLand.equals(new EmptyLand(10, 200, new Player(0,"","Game",""))));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money+3000);
    }
    @Test
    public void test_player_sell_an_villa_land_with_4000_dollars(){
        Player p = new Player(10000, "S", "孙美美", "");
        Land land = new Villa(10,  500, p);
        p.buyLand(land);
        double money = p.getMoney();
        Land soldLand = p.sellLand(land);
        assertTrue(soldLand.equals(new EmptyLand(10, 200, new Player(0,"","Game",""))));
        assertTrue(soldLand.getOwner().equals(new Player(0,"","Game","")));
        assertEquals(p.getMoney(), money + 4000);
    }

    @Test
    public void test_player_get_pass_fee_with_100_dollars(){
        GameMap m = new GameMap();
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        double moneyA = a.getMoney();
        m.createMap();
        s.buyLand(m.getLand(10));
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(m.getLand(10).getConstructPrice()/2);
        s.getPassFee(m.getLand(10).getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-100);
        assertEquals(s.getMoney(), moneyS+100);
    }

    @Test
    public void test_player_get_pass_fee_with_200_dollars(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land cottage = new Cottage(10, 200, s);
        double moneyA = a.getMoney();
        s.buyLand(cottage);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(cottage.getConstructPrice()/2);
        s.getPassFee(cottage.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-200);
        assertEquals(s.getMoney(), moneyS+200);
    }

    @Test
    public void test_player_get_pass_fee_with_300_dollars(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land land = new House(10, 200, s);
        double moneyA = a.getMoney();
        s.buyLand(land);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(land.getConstructPrice()/2);
        s.getPassFee(land.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-300);
        assertEquals(s.getMoney(), moneyS+300);
    }

    @Test
    public void test_player_get_pass_fee_with_400_dollars(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land cottage = new Villa(10,  200, s);
        double moneyA = a.getMoney();
        s.buyLand(cottage);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(cottage.getConstructPrice()/2);
        s.getPassFee(cottage.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-400);
        assertEquals(s.getMoney(), moneyS+400);
    }



    @Test
    public void test_player_get_pass_fee_with_150_dollars_for_300_original_price(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land land = new EmptyLand(10,  300, s);
        double moneyA = a.getMoney();
        s.buyLand(land);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(land.getConstructPrice()/2);
        s.getPassFee(land.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-150);
        assertEquals(s.getMoney(), moneyS+150);
    }

    @Test
    public void test_player_get_pass_fee_with_300_dollars_for_300_original_price(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land land = new Cottage(10,  300, s);
        double moneyA = a.getMoney();
        s.buyLand(land);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(land.getConstructPrice()/2);
        s.getPassFee(land.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-300);
        assertEquals(s.getMoney(), moneyS+300);
    }

    @Test
    public void test_player_get_pass_fee_with_450_dollars_for_300_original_price(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land land = new House(10,  300, s);
        double moneyA = a.getMoney();
        s.buyLand(land);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(land.getConstructPrice()/2);
        s.getPassFee(land.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-450);
        assertEquals(s.getMoney(), moneyS+450);
    }

    @Test
    public void test_player_get_pass_fee_with_600_dollars_for_300_original_price(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land land = new Villa(10,  300, s);
        double moneyA = a.getMoney();
        s.buyLand(land);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(land.getConstructPrice()/2);
        s.getPassFee(land.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-600);
        assertEquals(s.getMoney(), moneyS+600);
    }

    @Test
    public void test_player_get_pass_fee_with_250_dollars_for_500_original_price(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land land = new EmptyLand(10,  500, s);
        double moneyA = a.getMoney();
        s.buyLand(land);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(land.getConstructPrice()/2);
        s.getPassFee(land.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-250);
        assertEquals(s.getMoney(), moneyS+250);
    }

    @Test
    public void test_player_get_pass_fee_with_500_dollars_for_500_original_price(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land land = new Cottage(10,  500, s);
        double moneyA = a.getMoney();
        s.buyLand(land);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(land.getConstructPrice()/2);
        s.getPassFee(land.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-500);
        assertEquals(s.getMoney(), moneyS+500);
    }

    @Test
    public void test_player_get_pass_fee_with_750_dollars_for_500_original_price(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land land = new House(10,  500, s);
        double moneyA = a.getMoney();
        s.buyLand(land);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(land.getConstructPrice()/2);
        s.getPassFee(land.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-750);
        assertEquals(s.getMoney(), moneyS+750);
    }

    @Test
    public void test_player_get_pass_fee_with_1000_dollars_for_500_original_price(){
        Player s = new Player(10000, "S", "孙美美", "");
       Player a = new Player(10000, "A", "阿土伯", "");
        Land land = new Villa(10,  500, new Player(0,"", "Game", ""));
        double moneyA = a.getMoney();
        s.buyLand(land);
        double moneyS = s.getMoney();
        a.setCurrentPosition(10);
        a.payPassFee(land.getConstructPrice()/2);
        s.getPassFee(land.getConstructPrice()/2);
        assertEquals(a.getMoney(), moneyA-1000);
        assertEquals(s.getMoney(), moneyS+1000);
    }
    
    @Test
    public void test_player_buy_bomb(){
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setPoints(200);
        Tool bomb = new Bomb();
        assertTrue(s.buyTool(bomb));
        assertEquals(200 - 50, s.getPoints());
    }
    @Test
    public void test_player_buy_block(){
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setPoints(200);
        Tool block = new Block();
        assertTrue(s.buyTool(block));
        assertEquals(200-50, s.getPoints());
    }

    @Test
    public void test_player_buy_robot(){
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setPoints(200);
        Tool robot = new Robot();
        assertTrue(s.buyTool(robot));
        assertEquals(200-30, s.getPoints());

    }
    

}

       
    
