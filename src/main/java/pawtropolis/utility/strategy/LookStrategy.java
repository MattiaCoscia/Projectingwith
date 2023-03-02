package pawtropolis.utility.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

@Component
public class LookStrategy implements ActionStrategy{
    @Autowired
    private Player player;
    @Autowired
    private GameMap map;
    @Override
    public ActionEnum execute(String action) {
        Room actualRoom = map.getRooms()[player.getPositionY()][player.getPositionX()];
        System.out.println("Actual Room " + actualRoom.getName());
        System.out.println("Items in this room:");
        StringBuilder items = new StringBuilder();
        for (String s : actualRoom.getItems().keySet()) {
            items.append(s).append(" x").append(actualRoom.getItems().get(s).size()).append(" | ");
        }
        System.out.println((items.toString()));
        System.out.println("Npcs in this room:");
        StringBuilder npcs = new StringBuilder();
        for (Entity s : actualRoom.getEntities()) {
            npcs.append(s.getName()).append(" | ");
        }
        System.out.println(npcs);
        return ActionEnum.LOOK;
    }

    public LookStrategy(){

    }

    public LookStrategy(Player player,GameMap gameMap){
        this.player = player;
        this.map = gameMap;
    }
}
