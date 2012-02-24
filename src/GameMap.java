import org.fusesource.jansi.AnsiConsole;

import java.util.ArrayList;

public class GameMap {

    private ArrayList<Land> map;
    private int landNum;

    public GameMap(){
        landNum = 70;
        createMap();
    }


    public void createMap() {
        map = new ArrayList<Land>(landNum);
        for (int i = 0; i < 70; i++) {
            if (i < 64) {
                switch (i) {
                    case 0:
                        map.add(new StartLand(0, 0, new Player(0,"","Game","")));
                        break;
                    case 14:
                        map.add(new Hospital(14, 0, new Player(0,"","Game","")));
                        break;
                    case 28:
                        map.add(new ToolHouse(23, 0, new Player(0,"","Game","")));
                        break;
                    case 35:
                        map.add(new GiftHouse(35, 0, new Player(0,"","Game","")));
                        break;
                    case 49:
                        map.add(new Jail(49, 0, new Player(0,"","Game","")));
                        break;
                    case 63:
                        map.add(new MagicLand(63, 0, new Player(0,"","Game","")));
                        break;
                    default:
                        map.add(new EmptyLand(i, 0, new Player(0,"","Game","")));
                }
            } else {
                map.add(new Mine(i, 0, new Player(0,"","Game","")));
                setMinePoints(map, i);
            }

        }
        setLandsPrice();
    }

    private void setMinePoints(ArrayList<Land> map, int position) {
        Land mine = map.get(position);
        switch(position){
            case 64:
                mine.setPoints(20);
                break;
            case 65:
                mine.setPoints(80);
                break;
            case 66:
                mine.setPoints(100);
                break;
            case 67:
                mine.setPoints(40);
                break;
            case 68:
                mine.setPoints(80);
                break;
            case 69:
                mine.setPoints(60);
        }
    }

    private void setLandsPrice() {
        for (int i = 1; i < 14; i++) {
            map.get(i).setTwoPrice(200.);
        }
        for (int i = 15; i < 28; i++) {
            map.get(i).setTwoPrice(200.);
        }
        for (int i = 29; i < 35; i++) {
            map.get(i).setTwoPrice(500.);
        }
        for (int i = 36; i < 49; i++) {
            map.get(i).setTwoPrice(300.);
        }
        for (int i = 50; i < 63; i++) {
            map.get(i).setTwoPrice(300.);
        }

    }

    public Land getLand(int landID) {
        return map.get(landID);
    }

    public String print(ArrayList<Player> players, ArrayList<Tool> tools) {
        
            for (int i = 0; i < 29; i++) {
                if (printPlayersPosition(players, i)) {
                    if (i == 28)
                        System.out.println();
                    continue;
                }
                if (printTool(i, tools)) {
                    if (i == 28)
                        System.out.println();
                    continue;
                }
                printColor(i);
                if (i == 28) {
                    System.out.println();
                }
            }
            for (int i = 29; i < 35; i++) {
                int temp = 69 - (i - 29);
                if (printPlayersPosition(players, temp)) {
                    //System.out.print("                           ");

                }else{
                    if (printTool(temp, tools)) {
                        //System.out.print("                           ");
                        //continue;
                    } else {
                        printColor(temp);
                    }
                }
                System.out.print("                           ");
                if (printPlayersPosition(players, i)) {
                    System.out.println();
                    continue;

                }
                if (printTool(i, tools)) {
                    System.out.println();
                    continue;
                }
                printColor(i);
                System.out.println();
            }
            for (int i = 63; i > 34; i--) {
                if (printPlayersPosition(players, i)) {
                    if (i == 35)
                        System.out.println();
                    continue;
                }
                if (printTool(i, tools)) {
                    if (i == 35)
                        System.out.println();
                    continue;
                }
                printColor(i);
                if (i == 35)
                    System.out.println();
            }

            return " ";

    }

    private void printColor(int position) {
        if (!map.get(position).getOwner().isEmpty()){
            String color = map.get(position).getOwner().getColor();
            AnsiConsole.out.print(color);
            System.out.print(map.get(position).getLandForm());
            AnsiConsole.out.print( Game.END);
        }else
            System.out.print(map.get(position).getLandForm());
    }

    private boolean printTool(int pos, ArrayList<Tool> tools) {
            if (!tools.get(pos).isEmpty()) {
                System.out.print(tools.get(pos).getSymbol());
                return true;
            }
            return false;
    }

    private boolean printPlayersPosition(ArrayList<Player> players, int printNum) {

        for (Player p : players) {
            if (printNum == p.getCurrentPosition() && !p.getCovered()) {
                System.out.print(p.getSymbol());
                return true;
            }
        }
        return false;
    }

    public void setLand(int index, Land land) {
        map.set(index, land);
    }
}
