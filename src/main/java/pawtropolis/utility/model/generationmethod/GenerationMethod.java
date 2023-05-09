package pawtropolis.utility.model.generationmethod;

import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.ItemStored;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.Door;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.NpcsFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class GenerationMethod {
    public abstract GameMap generateMap(Player player, List<ItemStored> itemStoreds);

    protected void addItemsToRoom(List<ItemStored> itemStoreds, Room room, Random random) {
        for (ItemStored i : itemStoreds) {
            int qnt = random.nextInt(1,5);
            if (random.nextInt(3) > 1) {
                ItemStored itemStoredToAdd = room.getItem(i.getItemBlueprint().getName());
                if(itemStoredToAdd == null){
                    itemStoredToAdd = new ItemStored(i.getItemBlueprint(),qnt);
                    room.addItem(itemStoredToAdd);
                }else{
                    itemStoredToAdd.setQuantity(itemStoredToAdd.getQuantity()+qnt);
                }
            }
        }
    }

    protected void addNpcsToRoom(Room room, Random random) {
        int qnt = random.nextInt(1,4);
        if (random.nextInt(4) > 1) {
            for (int q = 0; q < qnt; q++) {
                Entity ent=NpcsFactory.getInstanceEntity((random.nextInt(0,3)));
                ent.setPositionX(room.getPositionX());
                ent.setPositionY(room.getPositionY());
                ent.setName(qnt+"N"+room.getChainPosition()+""+q+" ("+ent.getClass().getSimpleName()+")");
                room.getEntities().add(ent);
            }
        }
    }

    protected void setKeysToDoors(Room room, Random random){
        Map<String,ItemStored> itemsInRoom = room.getInventory().getItems();
        List<Door> adiancentDoors = new ArrayList<>(room.getAdiacentDoors().values());
        if(itemsInRoom.size() > 0){
            itemsInRoom.forEach((name,item) -> {
                if(!adiancentDoors.isEmpty()){
                    int posToUse = random.nextInt(adiancentDoors.size());
                    Door doorToLock = adiancentDoors.remove(posToUse);
                    if(doorToLock != null && doorToLock.getKeyItem() == null && !doorToLock.isOpen()){
                        doorToLock.setKeyItem(item);
                        doorToLock.setOpen(false);
                    };
                }
            });
        }
        adiancentDoors.forEach(door -> {
            if(!door.isOpen() && door.getKeyItem() == null){
                door.setOpen(true);
            }
        });
    }
}
