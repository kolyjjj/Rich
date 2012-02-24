import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Game {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String END = "\u001B[m";

    private ArrayList<Player> players;
    private GameMap map;
    private ArrayList<Tool> tools;

    public Game() {
        players = new ArrayList<Player>();
        map = new GameMap();
        tools = new ArrayList<Tool>(70);
        initTools();
        createMap();
    }

    private void initTools() {
        for (int i = 0; i < 70; i++) {
            tools.add(new EmptyTool());
        }
    }

    public String printWelcomeMessage() {
        System.out.println("欢迎来到大富翁的世界！");
        return "欢迎来到大富翁的世界！";
    }

    public String printChoosePlayserMessage() {
        System.out.println("请选择2～4位不重复玩家，输入编号，按回车结束输入。（1，钱夫人；2，阿土伯；3，孙美美；4，金贝贝。）");
        return "请选择2～4位不重复玩家，输入编号，按回车结束输入。（1，钱夫人；2，阿土伯；3，孙美美；4，金贝贝。）";
    }

    public boolean getPlayers(String input) {
        if (input.length() < 2) {
            System.out.println("您必须选择至少两个人物!");
            return false;
        } else if (input.length() > 4) {
            System.out.println("您选择了多余的人物！只能输入最多4个数字！");
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            int temp = input.codePointAt(i) - 48;
            if (temp < 1 || temp > 4) {
                System.out.println("您输入了无效的数字，游戏退出！");
                return false;
            }
            for (int j = i + 1; j < input.length(); j++) {
                if (temp == (input.codePointAt(j) - 48)) {
                    System.out.println("您选择了两个相同的人物，游戏退出！");
                    return false;
                }
            }
            //System.out.print(" " + (input.codePointAt(i) - 48));
        }

        return true;
    }

    public String choosePlayers(String input) {
        System.out.println("您选择的人物为：");
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            int temp = input.codePointAt(i) - 48;
            switch (temp) {
                case 1:
                    System.out.print("1,钱夫人;");
                    output += "1,钱夫人;";
                    players.add(new Player(500, "Q", "钱夫人", YELLOW));
                    break;
                case 2:
                    System.out.print("2,阿土伯;");
                    output += "2,阿土伯;";
                    players.add(new Player(10000, "A", "阿土伯", GREEN));
                    break;
                case 3:
                    System.out.print("3,孙美美;");
                    output += "3,孙美美;";
                    players.add(new Player(10000, "S", "孙美美", RED));
                    break;
                case 4:
                    System.out.print("4,金贝贝;");
                    output += "4,金贝贝;";
                    players.add(new Player(10000, "J", "金贝贝", PURPLE));
                    break;
            }
        }
        System.out.println();
        return output;
    }

    public void createMap() {
        map.createMap();
    }


    public String printMap() {
        return map.print(players, tools);
    }

    public void setPlayersPosition(int index, int position) {
        players.get(index).setCurrentPosition(position);
    }

    public String getInput(String command) {
        Scanner s = new Scanner(command);
        return s.next();
    }

    public void run() {
        printWelcomeMessage();
        printChoosePlayserMessage();
        Scanner s = new Scanner(System.in);
        String command = s.next();
        if (!getPlayers(command)) {
            return;
        }
        choosePlayers(command);
        createMap();
        printMap();
        Iterator<Player> iter;
//       for (Player i : players) {
//            i.setCurrentPosition(50);
//            i.setPoints(200);
//        }
        while (true) {
            iter = players.iterator();
            while (iter.hasNext()) {
                Player p = iter.next();
                if (isSkipRound(p))
                    continue;
                while (true) {
                    System.out.print(prompt(p));
                    Scanner sc = new Scanner(System.in);
                    command = sc.next();
                    String lowercaseCommand = command.toLowerCase();

                    handleCommandHelp(lowercaseCommand);
                    handleCommandQuery(p, lowercaseCommand);
                    handleCommandBomb(p, sc, lowercaseCommand);
                    handleCommandBlock(p, sc, lowercaseCommand);
                    handleCommandRobot(p, lowercaseCommand);
                    handleCommandSellToll(p, sc, lowercaseCommand);
                    handleCommandSellLand(p, sc, lowercaseCommand);

                    if (lowercaseCommand.equals("roll")) {
                        int pos = p.getCurrentPosition();
                        int steps = p.roll();
                        System.out.println("您前进了" + steps + "步！");
                        if (isEncounterBomb(p, pos, steps)) {
                            break;
                        }
                        if (isEncounterBlock(p, pos, steps)) {
                            System.out.println("嘿嘿，路障阻挡不了我强大的脚步！");
                        }
                        //player covered by other players or not
                        if (hasPlayer(p, players)) {
                            System.out.println("压死");
                        }else{
                            //System.out.println("没人被我压死哦");
                        }

                        printMap();
                        Land land = getLand(p.getCurrentPosition());

                        if (buyEmptyLand(iter, p, sc, land)) break;
                        if (updateLand(iter, p, sc, land)) break;
                        if (payPassFee(iter, p, land)) break;
                        if (atMineLand(p, land)) break;
                        if (atJail(p, land)) break;
                        if (atGiftHouse(p, sc, land)) break;
                        if (atToolHouse(p, sc, lowercaseCommand, land)) break;
                        if (atMagicHouse(land)) break;
                        if (atHospital(land)) break;

                    }

                    if (lowercaseCommand.equals("quit")) {
                        return;
                    }
                }
                if (gameOver(players)) {
                    return;
                }
            }
        }
    }

    private boolean atJail(Player p, Land land) {
        if (land.isJail()) {
            sendToJail(p);
            System.out.println("我没有犯罪，我是好人，相信我啊！");
            return true;
        }
        return false;
    }

    private boolean atHospital(Land land) {
        if (land.isHospital()) {
            System.out.println("医院不是随便让你进的啦！");
            return true;
        }
        return false;
    }

    private boolean atMagicHouse(Land land) {
        if (land.isMagicHouse()) {
            System.out.println("欢迎来到魔法屋，魔法世界正受伏地摩攻击，无法提供魔法！");
            return true;
        }
        return false;
    }

    private boolean atToolHouse(Player p, Scanner sc, String lowercaseCommand, Land land) {
        String command;
        if (land.isToolHouse()) {
            System.out.println("欢迎进入道具屋，请选择您所需要道具的编号：");
            System.out.println("道具    " + "编号  " + "价值（点数）  " + "显示方式");
            System.out.println("路障     " + "1    " + "50            " + "#");
            System.out.println("机器娃娃 " + "2    " + "30              ");
            System.out.println("炸弹     " + "3    " + "50            " + "@");
            if (p.getPoints() < 30) {
                System.out.println("对不起，您的点数不足，自动退出道具屋！");
                return true;
            }
            command = sc.next();
            Tool t;
            if (lowercaseCommand.equals("f")) {
                System.out.println("退出礼品屋！");
                return true;
            } else if (command.equals("1")) {
                t = new Block();
            } else if (command.equals("2")) {
                t = new Robot();
            } else if (command.equals("3")) {
                t = new Bomb();
            } else {
                System.out.println("您输入了无效的数字，自动退出道具屋！");
                return true;
            }

            buyTool(p, t);
            return true;
        }
        return false;
    }

    private boolean atGiftHouse(Player p, Scanner sc, Land land) {
        if (land.isGiftHouse()){
            System.out.println("欢迎进入礼品屋，请选择一件您喜欢的礼品");
            System.out.println("1,奖金；2，点数卡；3，福神。");
            int choice = sc.nextInt();
            String present = "";
            switch (choice) {
                case 1:
                    p.addMoney(2000);
                    present = "奖金";
                    break;
                case 2:
                    p.addPoints(200);
                    present = "点数卡";
                    break;
                case 3:
                    p.getGod();
                    present = "福神";
                    break;
            }
            if (present.isEmpty()) {
                System.out.println("您输入了错误的数字，自动退出礼品屋！");
                return true;
            } else {
                System.out.println("您选择了" + present + "礼品已生效，退出礼品屋！");
                return true;
            }
        }
        return false;
    }

    private boolean atMineLand(Player p, Land land) {
        if (land.isMine()) {
            p.addPoints(land.getPoints());
            System.out.println("经过矿地，您获得了" + land.getPoints() + "点数");
            return true;
        }
        return false;
    }

    private boolean payPassFee(Iterator<Player> iter, Player p, Land land) {
        if (!land.isGameLand() && !land.getOwner().equals(p)) {        //pay passing fees
            if (p.getGodDays() > 0) {
                System.out.println("福神附体，哈哈，不能给你钱哟！");
            } else if (land.getOwner().getJailDays() > 0) {
                System.out.println("地的主人在监狱中，免付过路费！");
            } else if (land.getOwner().getHospitalDays() > 0){
                System.out.println("地的主人在医院中，免付过路费！");
            }else{
                p.payPassFee(land.getConstructPrice() / 2);
                land.getOwner().getPassFee(land.getConstructPrice() / 2);
                System.out.println("经过" + land.getOwner().getName() + "土地" + "，支付" + land.getConstructPrice() / 2 + "元");
                bankrupt(iter, p);

            }
            return true;
        }
        return false;
    }

    private boolean updateLand(Iterator<Player> iter, Player p, Scanner sc, Land land) {
        String command;
        if (land.getOwner().equals(p) && !land.isFinal()) {                                                        //update a land
            System.out.println("是否升级该处地产，" + land.getOriginalPrice() + "元(Y/N)");
            command = sc.next();
            if (command.toLowerCase().equals("y")) {
                map.setLand(p.getCurrentPosition(), p.updateLand(land));
                bankrupt(iter, p);
            }
            return true;
        }
        return false;
    }

    private boolean buyEmptyLand(Iterator<Player> iter, Player p, Scanner sc, Land land) {
        String command;
        if (land.hasNoOwner()                   //buy an empty land
                && land.isEmptyLand()) {
            System.out.println("是否购买该处空地，" + land.getOriginalPrice() + "元(Y/N)");
            command = sc.next();
            if (command.toLowerCase().equals("y")) {
                p.buyLand(land);
                land.setOwner(p);
                System.out.println("您成功购买了一块土地，您还有" + p.getMoney() + "资金！");
                bankrupt(iter, p);

            }
            return true;
        }
        return false;
    }

    private void handleCommandSellLand(Player p, Scanner sc, String lowercaseCommand) {
        //sell a land
        if (lowercaseCommand.equals("sell")) {
            int landID = sc.nextInt();
            Land land = map.getLand(landID);
            if (landID < 0 || landID > 69 || p.getIndex(land) == -1) {
                System.out.println("您输入了错误的土地编号！");
            } else {
                map.setLand(landID, p.sellLand(land));
                System.out.println("您成功出售了一块土地，获得" + land.getConstructPrice() * 2 + "元");
            }
        }
    }

    private void handleCommandSellToll(Player p, Scanner sc, String lowercaseCommand) {
        if (lowercaseCommand.equals("selltool")) {
            if (p.hasNoTool()) {
                System.out.println("您没有道具可以出售!");
            } else {
                int toolIndex = sc.nextInt();
                if (sellTool(p, toolIndex)) {
                    System.out.println("成功出售道具");
                } else {
                    System.out.println("您输入了错误的道具编号！");
                }
            }
        }
    }

    private void handleCommandRobot(Player p, String lowercaseCommand) {
        if (lowercaseCommand.equals("robot")) {
            if (!p.hasTool("机器娃娃")) {
                System.out.println("您没有机器娃娃可以使用！");
            } else {
                robotGo(p);
                System.out.println("机器娃娃！清扫前方一切道具！goo...");
            }
        }
    }

    private void handleCommandBlock(Player p, Scanner sc, String lowercaseCommand) {
        if (lowercaseCommand.equals("block")) {
            if (!p.hasTool("路障")) {
                System.out.println("您没有路障可以使用！");
            } else {
                int pos = sc.nextInt();
                if (putTool(p, "路障", pos, players)) {
                    System.out.println("路障放置成功！");
                }
            }
        }
    }

    private void handleCommandBomb(Player p, Scanner sc, String lowercaseCommand) {
        if (lowercaseCommand.equals("bomb")) {
            if (!p.hasTool("炸弹")) {
                System.out.println("您没有炸弹可以使用！");

            } else {
                int pos = sc.nextInt();
                if (putTool(p, "炸弹", pos, players)) {
                    System.out.println("炸弹放置成功！");
                }
            }
        }
    }

    private void handleCommandQuery(Player p, String lowercaseCommand) {
        if (lowercaseCommand.equals("query")) {
            query(p);
        }
    }

    private void handleCommandHelp(String lowercaseCommand) {
        if (lowercaseCommand.equals("help")) {
            printHelpMessage();
        }
    }

    private boolean isSkipRound(Player p) {
        if (p.getJailDays() > 0) {
            System.out.println(p.getName() + "在监狱里，跳过ta的回合！");
            p.setDaysInJails(p.getJailDays() - 1);
            return true;
        }
        if (p.getHospitalDays() > 0){
            System.out.println(p.getName() + "在医院里，跳过ta的回合");
            p.setHospitalDays(p.getHospitalDays() - 1);
            return true;
        }
        if (p.getGodDays()>0) {
            p.setGodDays(p.getGodDays() - 1);
            System.out.println("福神还剩" + p.getGodDays() + "回合");
        }
        return false;
    }

    private boolean hasPlayer(Player p, ArrayList<Player> players) {
        boolean result = false;
        for (Player player : players) {
            if (p.getCurrentPosition() == player.getCurrentPosition() && !p.getName().equals(player.getName())) {
                player.setCovered(true);
                result = true;
            }
        }
        p.setCovered(false);
        return result;
    }

    private boolean gameOver(ArrayList<Player> players) {
        if (players.size() == 1) {
            System.out.println("游戏结束，最后的大赢家为：" + players.get(0).getName());
            return true;
        }
        return false;
    }

    private void bankrupt(Iterator<Player> iter, Player p) {
        if (p.getMoney() < 0) {
            System.out.println(p.getName() + "破产");
            resetPlayerLand(p);
            iter.remove();
        }
    }

    private void printHelpMessage() {
        System.out.println(
                "roll        掷骰子命令，行走1~6步。步数由随即算法产生。   \n" +
                "block n     玩家拥有路障后，可将路障放置到离当前位置前后10步的距离，任一玩家经过路障，都将被拦截。该道具一次有效。n 前后的相对距离，负数表示后方。\n" +
                "bomb n      可将路障放置到离当前位置前后10步的距离，任一玩家j 经过在该位置，将被炸伤，送往医院，住院三天。n 前后的相对距离，负数表示后方。\n" +
                "robot       使用该道具，可清扫前方路面上10步以内的其它道具，如炸弹、路障。\n" +
                "sell x      出售自己的房产，x 地图上的绝对位置，即地产的编号。\n" +
                "sellTool x  出售道具，x 道具编号\n" +
                "query       显示自家资产信息 \n" +
                "help        查看命令帮助   \n" +
                "quit        强制退出 ");
    }

    private boolean isEncounterBlock(Player p, int pos, int steps) {
        int position;
        for (int i = 1; i <= steps; i++) {
            position = pos+i;
            if (position > 69) {
                position -= 70;
            }
            if (hasTool(position, "路障")) {
                useTool(position);
                p.setCurrentPosition(pos + i);
                return true;
            }
        }
        return false;    
    }

    private void useTool(int position) {
        tools.set(position, new EmptyTool());
    }

    private boolean isEncounterBomb(Player p, int pos, int steps) {
        int position;
        for (int i = 1; i <= steps; i++) {
             position = pos+i;
            if (position > 69) {
                position -= 70;  
            }
            if (hasTool(position, "炸弹")) {
                useTool(position);
                sendToHospital(p);
                return true;
            }
        }
        return false;
    }



    public Land getLand(int currentPosition) {
        return map.getLand(currentPosition);
    }

    public String prompt(Player p) {
        //System.out.print(p.getName() + ">");
        return p.getName() + ">";
    }

    public void setLand(int index, Land land) {
        map.setLand(index, land);
    }

    public boolean buyTool(Player p, Tool t) {
        if (p.getPoints() < t.getPoints()) {
            System.out.println("您当前剩余的点数为：" + p.getPoints() +"，不足以购买" + t.getName() + "道具!");
            return false;
        }else if (p.getToolsNum() > 9) {
            System.out.println("您只能购买最多10个道具！");
            return false;
        } else {
            p.buyTool(t);
            System.out.println("您成功购买了" + t.getName() + "道具!");
            return true;
        }
    }

    public String query(Player p) {
        String result = " ";
        System.out.println("资金：" + p.getMoney());
        System.out.println("点数：" + p.getPoints());
        System.out.println("地产：" + "空地" + p.getLandNum("0") + "处；" + "茅屋" + p.getLandNum("1")
                + "处；" + "洋房" + p.getLandNum("2") + "处；" + "摩天楼" + p.getLandNum("3") + "处。");
        System.out.println("道具：" + "路障：" + p.getToolNum("路障") + "个;" + "炸弹:" +
                p.getToolNum("炸弹") + "个；" + "机器娃娃：" + p.getToolNum("机器娃娃") + "个");
        //printToolNum(p);
        //printLandLocation(p);
        return result;
    }

    public boolean putTool(Player p, String toolName, int position, ArrayList<Player> players) {
        if (toolName.equals("empty")) {
            return false;
        }
        if (position < -10 || position > 10) {
            System.out.println("您只能把道具放在前后十步的位置！");
            return false;
        }
        int pos = p.getCurrentPosition() + position;
        if ((pos) > 69) {
            pos -= 70;
        } else if (pos < 0) {
            pos += 70;
        }
        if (!tools.get(pos).isEmpty()) {
            System.out.println("那个地方已经有道具了！");
            return false;
        }
        for (Player player : players) {
            if (player.getCurrentPosition() == p.getCurrentPosition() + position) {
                System.out.println("那个地方有人，不能放道具！");
                return false;
            }
        }
        tools.set(pos, p.getTool(toolName));
        return true;
    }


    public boolean hasTool(int position, String  toolName) {
        return tools.get(position).getName().equals(toolName);
    }

    public void sendToHospital(Player player) {
        System.out.println("呜呜～～谁丢的炸弹啊！");
        player.setCurrentPosition(14);
        player.setHospitalDays(3);
        player.setCovered(false);
    }

    public void sendToJail(Player s) {
        s.setDaysInJails(2);
    }


    public boolean isNoTool(int pos) {
        return tools.get(pos).getName().equals("empty");
    }

    public void robotGo(Player p) {
        int pos = p.getCurrentPosition();
        for (int i = 1; i < 11; i++) {
            if (!isNoTool(pos+i)) {
                tools.set(pos+i, new EmptyTool());
            }
        }
    }

    public boolean sellTool(Player p, int toolIndex) {
        return p.sellTool(toolIndex);
    }

    public void resetPlayerLand(Player p) {
        for (int i = 0; i < 70; i++) {
            if (map.getLand(i).getOwner().equals(p)) {
                double price = map.getLand(i).getOriginalPrice();
                map.setLand(i, new EmptyLand(i, price, new Player(0,"","Game","")));
            }
        }
    }
}
