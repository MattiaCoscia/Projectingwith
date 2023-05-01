package pawtropolis.model.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

@Component
@Slf4j
public class LookStrategy implements ActionStrategy{
    @Autowired
    private Player player;
    @Autowired
    private GameMap map;
    @Override
    public ActionEnum execute(String action) {
        Room actualRoom = map.getRooms().get(map.giveKeyForRoom(player.getPositionY(),player.getPositionX()));
        log.info("Actual Room " + actualRoom.getName());
        log.info("Items in this room:");
        StringBuilder items = new StringBuilder();
        for (String s : actualRoom.getInventory().getItems().keySet()) {
            items.append(s).append(" x").append(actualRoom.getItem(s).getQuantity()).append(" | ");
        }
        log.info((items.toString()));
        log.info("Npcs in this room:");
        StringBuilder npcs = new StringBuilder();
        for (Entity s : actualRoom.getEntities()) {
            npcs.append(s.getName()).append(" | ");
        }
        log.info(npcs.toString());
        return ActionEnum.LOOK;
    }
}
