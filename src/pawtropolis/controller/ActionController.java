package pawtropolis.controller;

import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.util.*;

public class ActionController {
    private final Scanner sc=new Scanner(System.in);
    public void playerAction(GameMap map, Player player){
        String[] action = sc.nextLine().toLowerCase().split(" ");
        switch (action[0]){
            case "go":{
                go(action[1], player, map);
            }
            case "get":{
                get(action[1],player,map);
            }
        }
    }

    public void get(String s, Player player, GameMap map) {
        Room actualRoom=map.getRooms()[player.getPositionY()][player.getPositionX()];
        List<Item> itemsOftype=actualRoom.getItems().get(s);
        Item item=itemsOftype != null ? actualRoom.getItems().get(s).get(0) : null;
        if(item != null){
            if(player.getBag().getOccupiedSlots() + item.getVolume()<=player.getBag().getMaxSlots()){
                player.getBag().getItems().computeIfAbsent(s,k->new ArrayList<>()).add(item);
                actualRoom.getItems().get(s).remove(0);
                player.getBag().setOccupiedSlots(player.getBag().getOccupiedSlots() + item.getVolume());
                System.out.println(item.getName()+" has been put in the bag");
                if(itemsOftype.size() < 1){
                    actualRoom.getItems().remove(s,itemsOftype);
                }
            }else{
                System.out.println("the bag is full");
            }
        }
    }

    public void go(String direction,Player player, GameMap map){
        boolean nord=player.getPositionY() - 1 >= 0;
        boolean sud=player.getPositionY() + 1 < map.getRooms().length;
        boolean est=player.getPositionX() + 1 < map.getRooms()[0].length;
        boolean ovest=player.getPositionX() - 1 >= 0;
        switch (direction){
            case "est":{
                if(est && map.getRooms()[player.getPositionY()][player.getPositionX() + 1] != null && isRoomConnected(player,1,0,map)){
                    player.setPositionX(player.getPositionX() + 1);
                }
                break;
            }
            case "ovest":{
                if(ovest && map.getRooms()[player.getPositionY()][player.getPositionX() - 1] != null  && isRoomConnected(player,-1,0,map)){
                    player.setPositionX(player.getPositionX() - 1);
                }
                break;
            }
            case "nord":{
                if(nord && map.getRooms()[player.getPositionY() - 1][player.getPositionX()] != null  && isRoomConnected(player,0,-1,map)){
                    player.setPositionY(player.getPositionY() - 1);
                }
                break;
            }
            case "sud":{
                if(sud && map.getRooms()[player.getPositionY() + 1][player.getPositionX()] != null  && isRoomConnected(player,0,1,map)){
                    player.setPositionY(player.getPositionY() + 1);
                }
                break;
            }
        }
    }

    private boolean isRoomConnected(Player player, int changeInX, int changeInY, GameMap map){
        Room toGo=map.getRooms()[player.getPositionY() + changeInY][player.getPositionX() + changeInX];
        Room actualRoom=map.getRooms()[player.getPositionY()][player.getPositionX()];
        for(Room r:actualRoom.getAdiacentRooms()){
            if(r != null && r.equals(toGo)){
                return true;
            }
        }
        return false;
    }
}
