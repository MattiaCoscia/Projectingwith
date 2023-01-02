package pawtropolis.utility.model.generationMethod;

import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.Player;
import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.NpcsFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class GenerationMethod {
    public abstract GameMap generateMap(Player player, List<Item> items);

    protected void addItemsToRoom(List<Item> items, Room room) {
        for (Item i : items) {
            int qnt = (int) Math.ceil(Math.random() * 5);
            if ((Math.random() * 3) > 1) {
                for (int q = 0; q < qnt; q++) {
                    room.getItems().computeIfAbsent(i.getName(), k -> new ArrayList<>()).add(new Item(i.getName(), i.getDescription(), i.getVolume()));
                }
            }
        }
    }

    protected void addNpcsToRoom(Room room) {
        int qnt = (int) Math.ceil(Math.random() * 5);
        if ((Math.random() * 3) > 1) {
            for (int q = 0; q < qnt; q++) {
                Entity ent=NpcsFactory.getInstanceEntity(((int)Math.ceil(Math.random()*3)));
                ent.setPositionX(room.getPositionX());
                ent.setPositionY(room.getPositionY());
                ent.setName(qnt+"N"+room.getChainPosition()+""+q+" ("+ent.getClass().getSimpleName()+")");
                room.getEntities().add(ent);
            }
        }
    }
}
