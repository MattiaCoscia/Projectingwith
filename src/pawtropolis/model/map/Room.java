package pawtropolis.model.map;

import pawtropolis.model.entity.Entity;
import pawtropolis.utility.RoomType;
import pawtropolis.model.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String name;

    private RoomType type;
    private Map<String, List<Item>> items=new HashMap<>();
    private List<Entity> npcs=new ArrayList<>();
    private Room[] adiacentRooms=new Room[4];
    private int chainPosition;

    private int positionX;
    private int positionY;

    public Room(){
        this.type=RoomType.ROOM_TYPE;
    }

    public Room(String name, Map<String, List<Item>> items, List<Entity> npcs, int positionX, int positionY
    		, RoomType roomType, int chainPosition) {
        this.name = name;
        this.items = items;
        this.npcs = npcs;
        this.positionX=positionX;
        this.positionY=positionY;
        this.type=roomType;
        this.chainPosition=chainPosition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setItems(Map<String, List<Item>> items) {
        this.items = items;
    }

    public void setEntities(List<Entity> npcs) {
        this.npcs = npcs;
    }

    public void setAdiacentRooms(Room[] rooms) {
        this.adiacentRooms = rooms;
    }

    public Room setSingleRoom(int pos,Room room){
        this.adiacentRooms[pos]=room;
        return room;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    

	public void setChainPosition(int chainPosition) {
		this.chainPosition = chainPosition;
	}

    public String getName() {
        return name;
    }

    public Map<String, List<Item>> getItems() {
        return items;
    }

    public List<Entity> getEntities() {
        return npcs;
    }

    public Room[] getAdiacentRooms() {
        return adiacentRooms;
    }
    
    public Room getSingleRoom(int pos){return this.adiacentRooms[pos];}

    public RoomType getType() {
        return type;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

	public int getChainPosition() {
		return chainPosition;
	}
    
}
