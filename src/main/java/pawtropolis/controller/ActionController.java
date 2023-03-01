package pawtropolis.controller;

import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.util.*;

public class ActionController {
    private final Scanner sc = new Scanner(System.in);

    public String playerAction(GameMap map, Player player) {
        String[] action = sc.nextLine().toLowerCase().split(" ");
        switch (action[0]) {
            case "go": {
            	if(action.length <= 1 || action[1].replace(' ', '.').equals(".")) {
            		return null;
            	}
                return go(action[1], player, map);
            }
            case "get": {
            	if(action.length <= 1 || action[1].replace(' ', '.').equals(".")) {
            		return null;
            	}
                return get(action[1], player, map);
            }
            case "drop": {
            	if(action.length <= 1 || action[1].replace(' ', '.').equals(".")) {
            		return null;
            	}
                return drop(action[1], player, map);
            }
            case "bag": {
                return bag(player);
            }
            case "look": {
                return look(player,map);
            }
            default:{
                return "Unknown command";
            }
        }
    }
    
    private String bag(Player player) {
    	System.out.println("In your bag of volume "+player.getBag().getVolume()+" there are:");
        StringBuilder items = new StringBuilder();
        for (String s : player.getBag().getItems().keySet()) {
            items.append(s).append(" x").append(player.getBag().getItems().get(s).size()).append(" | ");
        }
        System.out.println((items.toString()));
        System.out.println("there is "+player.getBag().getOccupiedSlots()+" of volume occupied");
        return "bag";
    }
    
    private String look(Player player,GameMap map) {
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
        return "look";
    }

    private String get(String s, Player player, GameMap map) {
        Room actualRoom = map.getRooms()[player.getPositionY()][player.getPositionX()];
        List<Item> itemsOftype = actualRoom.getItems().get(s);
        Item item = itemsOftype != null ? actualRoom.getItems().get(s).get(0) : null;
        if (item != null) {
            if (item.getVolume() <= player.getBag().getVolume() - player.getBag().getOccupiedSlots()) {
                player.getBag().getItems().computeIfAbsent(s, k -> new ArrayList<>()).add(item);
                actualRoom.getItems().get(s).remove(0);
                player.getBag().setOccupiedSlots(player.getBag().getOccupiedSlots() + item.getVolume());
                System.out.println(item.getName() + " has been put in the bag");
                if (itemsOftype.isEmpty()) {
                    actualRoom.getItems().remove(s, itemsOftype);
                }
            } else {
                System.out.println("the bag is full");
            }
        }
        return "get";
    }

    private String drop(String s, Player player, GameMap map) {
        Room actualRoom = map.getRooms()[player.getPositionY()][player.getPositionX()];
        List<Item> itemsOftype = player.getBag().getItems().get(s);
        Item item = itemsOftype != null ? player.getBag().getItems().get(s).get(0) : null;
        if (item != null) {
            actualRoom.getItems().computeIfAbsent(s, k -> new ArrayList<>()).add(item);
            player.getBag().getItems().get(s).remove(0);
            System.out.println(item.getName() + " has been dropped in the room");
            if (itemsOftype.isEmpty()) {
                player.getBag().getItems().remove(s, itemsOftype);
            }
            player.getBag().setOccupiedSlots(player.getBag().getOccupiedSlots() - item.getVolume());
        }
        return "drop";
    }

    private String go(String direction, Player player, GameMap map) {
        boolean nord = player.getPositionY() - 1 >= 0;
        boolean sud = player.getPositionY() + 1 < map.getRooms().length;
        boolean est = player.getPositionX() + 1 < map.getRooms()[0].length;
        boolean ovest = player.getPositionX() - 1 >= 0;
        switch (direction) {
            case "east": {
                if (est && map.getRooms()[player.getPositionY()][player.getPositionX() + 1] != null && isRoomConnected(player, 1, 0, map)) {
                    player.setPositionX(player.getPositionX() + 1);
                }
                break;
            }
            case "west": {
                if (ovest && map.getRooms()[player.getPositionY()][player.getPositionX() - 1] != null && isRoomConnected(player, -1, 0, map)) {
                    player.setPositionX(player.getPositionX() - 1);
                }
                break;
            }
            case "north": {
                if (nord && map.getRooms()[player.getPositionY() - 1][player.getPositionX()] != null && isRoomConnected(player, 0, -1, map)) {
                    player.setPositionY(player.getPositionY() - 1);
                }
                break;
            }
            case "south": {
                if (sud && map.getRooms()[player.getPositionY() + 1][player.getPositionX()] != null && isRoomConnected(player, 0, 1, map)) {
                    player.setPositionY(player.getPositionY() + 1);
                }
                break;
            }
            default:{
                System.out.println("Unknown direction!");
            }
        }
        return "go";
    }

    private boolean isRoomConnected(Player player, int changeInX, int changeInY, GameMap map) {
        Room toGo = map.getRooms()[player.getPositionY() + changeInY][player.getPositionX() + changeInX];
        Room actualRoom = map.getRooms()[player.getPositionY()][player.getPositionX()];
        for (Room r : actualRoom.getAdiacentRooms()) {
            if (r != null && r.equals(toGo)) {
                return true;
            }
        }
        return false;
    }
}
