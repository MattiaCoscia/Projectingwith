package pawtropolis;

import pawtropolis.model.RoomType;
import pawtropolis.model.map.Room;
import pawtropolis.utility.GameMapGenerator;

import java.lang.reflect.InvocationTargetException;

public class Avvio {
    public static void main(String[] args) {
        try {
            GameMapGenerator.generateMap();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for(Room[] line:GameMapGenerator.gameMap.getRooms()){
            for(Room room:line){
                if(room!=null){
                    int relations=0;
                    for(Room r:room.getAdiacentRooms()){
                        if(r!=null){
                            relations++;
                        }
                    }
                    System.out.print("["+(room.getPositionX())+"|R"+relations+"|"+(room.getPositionY())+"]");
                }else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
