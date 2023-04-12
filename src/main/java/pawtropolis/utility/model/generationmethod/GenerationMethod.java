package pawtropolis.utility.model.generationmethod;

import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.ItemStored;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.NpcsFactory;

import java.util.List;
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
}
