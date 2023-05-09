package pawtropolis.model.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.Door;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomNameKeyGenerator;

@Component
@Slf4j
public class LookStrategy implements ActionStrategy{
    @Autowired
    private Player player;
    @Autowired
    private GameMap map;
    @Override
    public ActionEnum execute(String action) {
        Room actualRoom = map.getRooms().get(RoomNameKeyGenerator.giveKeyForRoom(player.getPositionY(),player.getPositionX()));
        log.info("Actual Room " + actualRoom.getName());
        log.info("Doors in this room:");
        StringBuilder doors = new StringBuilder();
        actualRoom.getAdiacentDoors().forEach((direction,door) -> {
            doors.append("\n"+direction.toString()).append(" is open: ").append(door.isOpen());
            if(!door.isOpen() && door.getKeyItem() != null){
                doors.append(" | key: ").append(door.getKeyItem().getItemBlueprint().getName()+" |");
            }
        });
        log.info(doors.toString());
        log.info("Items in this room:");
        StringBuilder items = new StringBuilder();
        for (String item : actualRoom.getInventory().getItems().keySet()) {
            items.append(item).append(" x").append(actualRoom.getItem(item).getQuantity()).append(" | ");
        }
        log.info((items.toString()));
        log.info("Npcs in this room:");
        StringBuilder npcs = new StringBuilder();
        for (Entity npc : actualRoom.getEntities()) {
            npcs.append(npc.getName()).append(" | ");
        }
        log.info(npcs.toString());
        return ActionEnum.LOOK;
    }
}
