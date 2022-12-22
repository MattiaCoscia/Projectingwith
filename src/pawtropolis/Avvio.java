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
                    System.out.print("[]");
                }else{
                    System.out.print(".");
                }
            }
            System.out.println("\n"+"-----------------------");
        }
    }
}
