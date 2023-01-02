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
            	if(action[1].replace(' ', '.').equals(".")) {
            		return null;
            	}
                return go(action[1], player, map);
            }
            case "get": {
            	if(action[1].replace(' ', '.').equals(".")) {
            		return null;
            	}
                return get(action[1], player, map);
            }
            case "drop": {
            	if(action[1].replace(' ', '.').equals(".")) {
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
        }
        return null;
    }
    
    private String bag(Player player) {
    	System.out.println("In your bag there are:");
        String items = "";
        for (String s : player.getBag().getItems().keySet()) {
            items += s + " x" + player.getBag().getItems().get(s).size() + " | ";
        }
        System.out.println((items));
        return "bag";
    }
    
    private String look(Player player,GameMap map) {
    	Room actualRoom = map.getRooms()[player.getPositionY()][player.getPositionX()];
        System.out.println("Actual Room " + actualRoom.getName());
        System.out.println("Items in this room:");
        String items = "";
        for (String s : actualRoom.getItems().keySet()) {
            items += s + " x" + actualRoom.getItems().get(s).size() + " | ";
        }
        System.out.println((items));
        System.out.println("Npcs in this room:");
        String npcs = "";
        for (Entity s : actualRoom.getEntities()) {
            npcs += s.getName() + " | ";
        }
        System.out.println(npcs);
        return "look";
    }

    private String get(String s, Player player, GameMap map) {
        Room actualRoom = map.getRooms()[player.getPositionY()][player.getPositionX()];
        List<Item> itemsOftype = actualRoom.getItems().get(s);
        Item item = itemsOftype != null ? actualRoom.getItems().get(s).get(0) : null;
        if (item != null) {
            if (player.getBag().getOccupiedSlots() + item.getVolume() <= player.getBag().getMaxSlots()) {
                player.getBag().getItems().computeIfAbsent(s, k -> new ArrayList<>()).add(item);
                actualRoom.getItems().get(s).remove(0);
                player.getBag().setOccupiedSlots(player.getBag().getOccupiedSlots() + item.getVolume());
                System.out.println(item.getName() + " has been put in the bag");
                if (itemsOftype.size() < 1) {
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
            if (itemsOftype.size() < 1) {
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
            case "est": {
                if (est && map.getRooms()[player.getPositionY()][player.getPositionX() + 1] != null && isRoomConnected(player, 1, 0, map)) {
                    player.setPositionX(player.getPositionX() + 1);
                }
                break;
            }
            case "ovest": {
                if (ovest && map.getRooms()[player.getPositionY()][player.getPositionX() - 1] != null && isRoomConnected(player, -1, 0, map)) {
                    player.setPositionX(player.getPositionX() - 1);
                }
                break;
            }
            case "nord": {
                if (nord && map.getRooms()[player.getPositionY() - 1][player.getPositionX()] != null && isRoomConnected(player, 0, -1, map)) {
                    player.setPositionY(player.getPositionY() - 1);
                }
                break;
            }
            case "sud": {
                if (sud && map.getRooms()[player.getPositionY() + 1][player.getPositionX()] != null && isRoomConnected(player, 0, 1, map)) {
                    player.setPositionY(player.getPositionY() + 1);
                }
                break;
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
