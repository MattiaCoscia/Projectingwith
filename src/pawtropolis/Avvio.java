package pawtropolis;

import pawtropolis.model.RoomType;
import pawtropolis.model.map.Room;
import pawtropolis.utility.GameMapGenerator;

public class Avvio {
    public static void main(String[] args) {
        GameMapGenerator.generateMap();
        for(Room[] line:GameMapGenerator.gameMap.getRooms()){
            for(Room room:line){
                if(room!=null){
                    int relations=0;
                    for(Room r:room.getAdiacentRooms()){
                        if(r!=null){
                            relations++;
                        }
                    }
                    System.out.print("["+(room.getPositionX()+1)+"|"+relations+"|"+(room.getPositionY()+1)+"]");
                }else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
