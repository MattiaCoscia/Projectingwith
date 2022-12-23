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
                    String relations="";
                    for(Room r:room.getAdiacentRooms()){
                        int i=0;
                        if(r!=null && r.getType().equals(RoomType.ROOM_TYPE)){
                            switch (i){
                                case 0:
                                    relations+="^";
                                case 1:
                                    relations+=">";
                                case 2:
                                    relations+="v";
                                case 3:
                                    relations+="<";
                            }
                        }
                        i++;
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
