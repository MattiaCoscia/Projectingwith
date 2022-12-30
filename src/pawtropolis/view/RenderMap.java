package pawtropolis.view;

import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

public class RenderMap {

    public static void printMap(GameMap map){
        for(Room[] line:map.getRooms()){
            String printLineHead="";
            String printLineBody="";
            String printLineFoot="";
            for(Room room:line){
                    //NORD = 0;
                // OVEST = 3 ; EAST= 1
                    // SUD = 2;
                if(room!=null){
                    String nord=room.getAdiacentRooms()[0] != null ? "|   |" : "+===+";
                    String sud=room.getAdiacentRooms()[2] != null ? "|   |" : "+===+";
                    String estOvest="";
                    if(room.getAdiacentRooms()[3] != null && room.getAdiacentRooms()[1] != null){
                        estOvest="     ";
                    }else if(room.getAdiacentRooms()[3] != null && room.getAdiacentRooms()[1] == null){
                        estOvest="    |";
                    }else if(room.getAdiacentRooms()[3] == null && room.getAdiacentRooms()[1] != null){
                        estOvest="|    ";
                    }else if(room.getAdiacentRooms()[3] == null && room.getAdiacentRooms()[1] == null){
                        estOvest="|   |";
                    }
                    if(room.getName().equals("Entry")) {
                    	estOvest=estOvest.replace(' ', 'E');
                    }
                    printLineHead+=nord;
                    printLineBody+=estOvest;
                    printLineFoot+=sud;
                }else{
                    printLineHead+="     ";
                    printLineBody+="     ";
                    printLineFoot+="     ";
                    //ok
                }
            }
            System.out.println(printLineHead+"\n"+printLineBody+"\n"+printLineFoot);
        }
    }
}
