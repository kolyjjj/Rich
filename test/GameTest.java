import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class GameTest {
    @Test
    public void test_print_welcome_message(){
        Game g = new Game();
        assertTrue(g.printWelcomeMessage().equals("欢迎来到大富翁的世界！"));
    }
    
    @Test
    public void test_print_choose_players_message(){
        Game g = new Game();
        assertTrue(g.printChoosePlayserMessage().equals("请选择2～4位不重复玩家，输入编号，按回车结束输入。（1，钱夫人；2，阿土伯；3，孙美美；4，金贝贝。）"));
    }
    
    @Test
    public void test_user_input_1234(){
        Scanner s = new Scanner("1234");
        String command = s.next();
        Game g = new Game();
        assertTrue(g.getPlayers(command));
    }

    @Test
    public void test_user_input_1(){
        Scanner s = new Scanner("1");
        String command = s.next();
        Game g = new Game();
        assertFalse(g.getPlayers(command));
    }

    @Test
    public void test_user_input_12345(){
        Scanner s = new Scanner("12345");
        String command = s.next();
        Game g = new Game();
        assertFalse(g.getPlayers(command));
    }

    @Test
    public void test_user_input_jk(){
        Scanner s = new Scanner("jk");
        String command = s.next();
        Game g = new Game();
        assertFalse(g.getPlayers(command));
    }
    
    @Test
    public void test_user_choose_players_12(){
        Scanner s = new Scanner("12");
        String command = s.next();
        Game g = new Game();
        assertTrue(g.choosePlayers(command).equals("1,钱夫人;2,阿土伯;"));
    }

    @Test
    public void test_user_choose_players_1234(){
        Scanner s = new Scanner("1234");
        String command = s.next();
        Game g = new Game();
        assertTrue(g.choosePlayers(command).equals("1,钱夫人;2,阿土伯;3,孙美美;4,金贝贝;"));
    }
    
    @Test
    public void test_print_player_prompt_message(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        assertTrue(g.prompt(s).equals("孙美美>"));
    }

    @Test
    public void test_print_game_map_without_player(){
        Game g = new Game();
        g.createMap();
        assertTrue(g.printMap().equals(" "));
    }
    
    @Test 
    public void test_print_game_map_with_4_players(){
        Game g = new Game();
        g.createMap();
        g.choosePlayers("1234");
        assertTrue(g.printMap().equals(" ")); 
    }
    
    @Test
    public void test_print_game_map_with_one_player_in_7(){
        Game g = new Game();
        g.createMap();
        g.choosePlayers("1234");
        g.setPlayersPosition(0, 7);                      //for qian fu ren
        assertTrue(g.printMap().equals(" "));
    }

    @Test
    public void test_print_game_map_with_one_player_in_Hospital(){
        Game g = new Game();
        g.createMap();
        g.choosePlayers("1234");
        g.setPlayersPosition(0, 14);                      //for qian fu ren
        assertTrue(g.printMap().equals(" "));
    }

    @Test
    public void test_print_game_map_with_one_player_in_18(){
        Game g = new Game();
        g.createMap();
        g.choosePlayers("1234");
        g.setPlayersPosition(0, 18);                      //for qian fu ren
        assertTrue(g.printMap().equals(" "));
    }

    @Test
    public void test_print_game_map_with_one_player_in_30(){
        Game g = new Game();
        g.createMap();
        g.choosePlayers("1234");
        g.setPlayersPosition(0, 30);                      //for qian fu ren
        assertTrue(g.printMap().equals(" "));
    }

    @Test
    public void test_print_game_map_with_one_player_in_46(){
        Game g = new Game();
        g.createMap();
        g.choosePlayers("1234");
        g.setPlayersPosition(0, 46);                      //for qian fu ren
        assertTrue(g.printMap().equals(" "));
    }

    @Test
    public void test_print_game_map_with_one_player_in_68(){
        Game g = new Game();
        g.createMap();
        g.choosePlayers("1234");
        g.setPlayersPosition(0, 68);                      //for qian fu ren
        assertTrue(g.printMap().equals(" "));
    }
    
    @Test
    public void test_player_roll(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "blue");
        String command  = g.getInput("roll");
        int steps = 0;
        int currentPosition = s.getCurrentPosition();
        if (command.toLowerCase().equals("roll")){
            steps = s.roll();
        }
        assertEquals(s.getCurrentPosition(), currentPosition+steps);
    }
    
    @Test
    public void test_player_buy_an_empty_land_with_input_y(){
        Player s = new Player(10000, "S", "孙美美", "color");
        Land land = new EmptyLand(10, 200., new Player(0,"","Game",""));
        s.setCurrentPosition(10);
        System.out.printf("是否购买该处空地，" + land.getOriginalPrice() + "元（Y/N）");
        Scanner sc =  new Scanner("y");
        String command = sc.next();
        double money = s.getMoney();
        if (command.toLowerCase().equals("y")){
            s.buyLand(land);
            land.setOwner(s);
        }
        assertEquals(money-200, s.getMoney());    
        assertTrue(land.getOwner().equals(s));
    }
    @Test
    public void test_player_not_buy_an_empty_land_with_input_n(){
        Player s = new Player(10000, "S", "孙美美", "color");
        Land land = new EmptyLand(10, 200., new Player(0,"","Game",""));
        s.setCurrentPosition(10);
        System.out.printf("是否购买该处空地，" + land.getOriginalPrice() + "元（Y/N）");
        Scanner sc =  new Scanner("n");
        String command = sc.next();
        double money = s.getMoney();
        if (command.toLowerCase().equals("y")){
            s.buyLand(land);
            land.setOwner(s);
        }
        assertEquals(money, s.getMoney());
        assertFalse(land.getOwner().equals(s));
    }
    
    @Test
    public void test_player_update_an_empty_land(){
        Game g = new Game();
        Player p = new Player(10000, "S",  "孙美美", "color");
        p.setCurrentPosition(10);
        Land originalLand = new EmptyLand(10,200.,p);
        g.setLand(10, originalLand);
        Land land = g.getLand(10);
        p.buyLand(land);
        double money = p.getMoney();
        if (!land.isFinal() && land.getOwner().equals(p)){
            g.setLand(p.getCurrentPosition(), p.updateLand(land));
        }
        assertTrue(g.getLand(p.getCurrentPosition()).equals(new Cottage(10, 200. ,p)));
        assertEquals(money-200, p.getMoney());
    }
    
    @Test
    public void test_player_pay_pass_fee_with_100(){
        Game g = new Game();
        Player s = new Player(1000, "S", "孙美美", "");
        Player a = new Player(1000, "A", "阿土伯", "");
        Land land = new EmptyLand(10, 200., new Player(0, "","Game",""));
        g.setLand(10, land);
        land = g.getLand(10);
        s.buyLand(land);
        land.setOwner(s);
        a.setCurrentPosition(10);
        Land land1 = g.getLand(a.getCurrentPosition());
        double moneyS = s.getMoney();
        double moneyA = a.getMoney();
        if (!land1.isGameLand() && !land1.getOwner().equals(a)){
            a.payPassFee(land1.getConstructPrice()/2);
            land1.getOwner().getPassFee(land1.getConstructPrice()/2);
        }
        assertEquals(moneyA-100, a.getMoney());
        assertEquals(moneyS+100, s.getMoney());
    }
    
    @Test
    public void test_player_get_20_points_when_in_mine_land_64(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setCurrentPosition(64);
        Land land = g.getLand(s.getCurrentPosition());
        int points = s.getPoints();
        if (land.isMine()){
            s.addPoints(land.getPoints());
        }
        assertEquals(points+20, s.getPoints());
    }

    @Test
    public void test_player_get_80_points_when_in_mine_land_65(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setCurrentPosition(65);
        Land land = g.getLand(s.getCurrentPosition());
        int points = s.getPoints();
        if (land.isMine()){
            s.addPoints(land.getPoints());
        }
        assertEquals(points+80, s.getPoints());
    }

    @Test
    public void test_player_get_100_points_when_in_mine_land_66(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setCurrentPosition(66);
        Land land = g.getLand(s.getCurrentPosition());
        int points = s.getPoints();
        if (land.isMine()){
            s.addPoints(land.getPoints());
        }
        assertEquals(points+100, s.getPoints());
    }

    @Test
    public void test_player_get_40_points_when_in_mine_land_67(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setCurrentPosition(67);
        Land land = g.getLand(s.getCurrentPosition());
        int points = s.getPoints();
        if (land.isMine()){
            s.addPoints(land.getPoints());
        }
        assertEquals(points+40, s.getPoints());
    }

    @Test
    public void test_player_get_80_points_when_in_mine_land_68(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setCurrentPosition(68);
        Land land = g.getLand(s.getCurrentPosition());
        int points = s.getPoints();
        if (land.isMine()){
            s.addPoints(land.getPoints());
        }
        assertEquals(points+80, s.getPoints());
    }

    @Test
    public void test_player_get_60_points_when_in_mine_land_69(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setCurrentPosition(69);
        Land land = g.getLand(s.getCurrentPosition());
        int points = s.getPoints();
        if (land.isMine()){
            s.addPoints(land.getPoints());
        }
        assertEquals(points+60, s.getPoints());
    }

    @Test
    public void test_player_come_to_jail_with_49(){
        Game g =new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setCurrentPosition(49);
        Land land = g.getLand(s.getCurrentPosition());
        if (land.isJail()){
            g.sendToJail(s);
        }
        assertEquals(s.getJailDays(), 2);
    }
    
    @Test
    public void test_player_get_gift_point_card_with_35(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setCurrentPosition(35);
        Land land = g.getLand(s.getCurrentPosition());
        int points = s.getPoints();
        if (land.isGiftHouse()){
            s.addPoints(200);
        }
        assertEquals(points+200, s.getPoints());
    }

    @Test
    public void test_player_get_gift_money_with_35(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setCurrentPosition(35);
        Land land = g.getLand(s.getCurrentPosition());
        double money = s.getMoney();
        if (land.isGiftHouse()){
            s.addMoney(2000);
        }
        assertEquals(money+2000, s.getMoney());
    }

    @Test
    public void test_player_get_gift_god_with_35(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setCurrentPosition(35);
        Land land = g.getLand(s.getCurrentPosition());
        if (land.isGiftHouse()){
            s.getGod();
        }
        assertEquals(s.getGodDays(), 5);
    }
    
    @Test
    public void test_player_with_god_pay_no_pass_fee(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        Land land = g.getLand(10);
        s.buyLand(land);
        a.getGod();
        a.setCurrentPosition(10);
        double moneyA = a.getMoney();
        double moneyS = s.getMoney(); 
        if (a.getGodDays() > 0){
            System.out.println("with god, no fee!");
        }else{
            double passFee = land.getConstructPrice() / 2;
            a.payPassFee(passFee);
            s.getPassFee(passFee);
        }
        assertEquals(moneyA, a.getMoney());
        assertEquals(moneyS, s.getMoney());
    }

    @Test
    public void test_player_with_owner_in_jail_pay_no_pass_fee(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        Land land = g.getLand(10);
        s.buyLand(land);
        s.setCurrentPosition(49);
        g.sendToJail(s);
        a.setCurrentPosition(10);
        double moneyA = a.getMoney();
        double moneyS = s.getMoney();
        if (s.getJailDays() > 0){
            System.out.println("Owner's in jail, no fee!");
        }else{
            double passFee = land.getConstructPrice() / 2;
            a.payPassFee(passFee);
            s.getPassFee(passFee);
        }
        assertEquals(moneyA, a.getMoney());
        assertEquals(moneyS, s.getMoney());
    }
    
    @Test
    public void test_player_buy_robot(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setPoints(200);
        s.setCurrentPosition(28);
        Land land = g.getLand(s.getCurrentPosition());
        if (land.isToolHouse()) {
            assertTrue(g.buyTool(s, new Robot()));
        }
        assertEquals(200-30, s.getPoints());
    }

    @Test
    public void test_player_buy_bomb(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setPoints(200);
        s.setCurrentPosition(28);
        Land land = g.getLand(s.getCurrentPosition());
        if (land.isToolHouse()) {
            assertTrue(g.buyTool(s, new Bomb()));
        }
        assertEquals(200-50, s.getPoints());
    }

    @Test
    public void test_player_buy_block(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setPoints(200);
        s.setCurrentPosition(28);
        Land land = g.getLand(s.getCurrentPosition());
        if (land.isToolHouse()) {
            assertTrue(g.buyTool(s, new Block()));
        }
        assertEquals(200-50, s.getPoints());
    }
    
    @Test
    public void test_player_want_to_buy_11th_tool(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setPoints(700);
        for (int i = 0; i < 10; i++) {
            s.buyTool(new Bomb());
        }
        s.setCurrentPosition(28);
        Land land = g.getLand(s.getCurrentPosition());
        if (land.isToolHouse()) {
            assertFalse(g.buyTool(s, new Block()));
        }
        assertEquals(200, s.getPoints());
    }

    @Test
    public void test_player_no_points_to_buy_a_tool(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setPoints(7);
        s.setCurrentPosition(28);
        Land land = g.getLand(s.getCurrentPosition());
        if (land.isToolHouse()) {
            assertFalse(g.buyTool(s, new Block()));
        }
        assertEquals(7, s.getPoints());
    }
    
    @Test
    public void test_player_query_assets(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        String result = g.query(s);
        assertTrue(" ".equals(result));
    }
    
    @Test
    public void test_player_throw_bomb_4(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(s);
        players.add(a);
        s.setPoints(200);
        s.buyTool(new Bomb());
        g.putTool(s, "炸弹", 4, players);
        int position =  a.getCurrentPosition();
        a.proceed(6);
        for (int i = 1; i < 7; i++) {
            if (g.hasTool(position + i, "炸弹")) {
                g.sendToHospital(a);
            }
        }
        assertTrue(a.getHospitalDays() > 0);
    }

    @Test
    public void test_player_throw_bomb_minus_4(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(s);
        players.add(a);
        s.setPoints(200);
        s.buyTool(new Bomb());
        g.putTool(s, "炸弹", -4, players);
        a.setCurrentPosition(61);
        int position =  a.getCurrentPosition();
        a.proceed(6);
        for (int i = 1; i < 7; i++) {
            if (g.hasTool(position + i, "炸弹")) {
                g.sendToHospital(a);
            }
        }
        assertTrue(a.getHospitalDays() > 0);
    }

    @Test
    public void test_player_throw_bomb_minus_11(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(s);
        players.add(a);
        s.setPoints(200);
        s.buyTool(new Bomb());
        assertFalse(g.putTool(s, "炸弹", -11, players));
        a.setCurrentPosition(61);
        int position =  a.getCurrentPosition();
        a.proceed(6);
        for (int i = 1; i < 7; i++) {
            if (g.hasTool(position + i, "炸弹")) {
                g.sendToHospital(a);
            }
        }
        assertTrue(a.getHospitalDays() == 0);
    }

    @Test
    public void test_player_throw_bomb_11(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(s);
        players.add(a);
        s.setPoints(200);
        s.buyTool(new Bomb());
        assertFalse(g.putTool(s, "炸弹", 11, players));
        a.setCurrentPosition(6);
        int position =  a.getCurrentPosition();
        a.proceed(6);
        for (int i = 1; i < 7; i++) {
            if (g.hasTool(position + i, "炸弹")) {
                g.sendToHospital(a);
            }
        }
        assertTrue(a.getHospitalDays() == 0);
    }
    
    @Test
    public void test_bomb_can_not_be_put_if_has_a_tool(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(s);
        players.add(a);
        s.setPoints(200);
        s.buyTool(new Bomb());
        assertTrue(g.putTool(s, "炸弹", 8, players));
        a.setPoints(200);
        a.buyTool(new Bomb());
        assertFalse(g.putTool(a, "炸弹", 8, players));
    }
    
    @Test
    public void test_bomb_cannot_be_put_if_has_player(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(s);
        players.add(a);
        s.setPoints(200);
        s.buyTool(new Bomb());
        a.setCurrentPosition(8);
        assertFalse(g.putTool(s, "炸弹",8, players));
        assertFalse(g.putTool(s, "炸弹",0, players));
    }
    
    @Test
    public void test_player_use_block(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(s);
        players.add(a);
        s.setPoints(200);
        s.buyTool(new Block());
        assertTrue(g.putTool(s, "路障", 4, players));
        int position =  a.getCurrentPosition();
        a.proceed(6);
        for (int i = 1; i < 7; i++) {
            if (g.hasTool(position + i, "路障")) {
                a.setCurrentPosition(position+i);
            }
        }
        assertEquals(4, a.getCurrentPosition());
    }
    
    @Test
    public void test_player_use_robot(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        s.buyTool(new Robot());
        a.buyTool(new Bomb());
        int pos = s.getCurrentPosition();
        g.robotGo(s);
        boolean noTool = true;
        for (int i = 1; i < 11; i++){
            if (!g.isNoTool(pos+i)){
                noTool = false;
            }
        }
        assertTrue(noTool);
    }
    
    @Test
    public void test_player_sell_a_tool(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setPoints(50);
        s.buyTool(new Bomb());
        g.sellTool(s, 0);
        assertFalse(s.hasTool("炸弹"));
        assertEquals(50, s.getPoints());
    }

    @Test
    public void test_player_sell_a_tool_with_wrong_index(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        s.setPoints(50);
        s.buyTool(new Bomb());
        assertFalse(g.sellTool(s, 1));
        assertTrue(s.hasTool("炸弹"));
        assertEquals(0, s.getPoints());
    }
    
    @Test
    public void test_player_out_of_money(){
        Game g = new Game();
        Player s = new Player(10000, "S", "孙美美", "color");
        Player a = new Player(10000, "A", "阿土伯", "color");
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(s);
        players.add(a);
        a.setMoney(-1);
        Iterator<Player> iter = players.iterator();
        while (iter.hasNext()) {
            Player p =iter.next();
            if (p.getMoney() < 0) {
                System.out.println(p.getName() + "破产");
                g.resetPlayerLand(p);
                iter.remove();
                break;
            }
        }
        assertEquals(1, players.size());
    }



}
