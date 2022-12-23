package pawtropolis;

import pawtropolis.model.map.Room;
import pawtropolis.utility.GameMapGenerator;

public class Avvio {
    public static void main(String[] args) {
        GameMapGenerator.generateMap();
        int x=0;
        int y=0;
        System.out.println("DIRECTION MENU:   ^^^:NORTH   ==>:WEST  VVV:SOUTH  <==:EST  ");
        for(Room[] line:GameMapGenerator.gameMap.getRooms()){
            x=0;
            for(Room room:line){
                if(room!=null){
                    for(Room r:room.getAdiacentRooms()){
                    }
                    System.out.print(" "+room.getName()+" ");
                }else{
                    System.out.print("|");
                }
                x++;
            }
            System.out.println("y:"+y);
            y++;
        }
    }
}
