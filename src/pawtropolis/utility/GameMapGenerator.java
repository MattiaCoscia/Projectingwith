package pawtropolis.utility;

import pawtropolis.model.RoomType;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.util.HashMap;

public class GameMapGenerator {
    public static final GameMap gameMap=new GameMap(new Room[5][5]);


    public static void generateMap(){
        gameMap.getRooms()[0][0]=new Room("Entry",new HashMap<>(),new HashMap<>(),0,0);
        Room entryRoom=gameMap.getRooms()[0][0];
        int maxAdiacentRooms= (int)Math.floor(Math.random()*3);
        for(int i=0;i<maxAdiacentRooms;i++){
            int adiacentPosition=2-((int)Math.floor(Math.random()*2));//NORD = 0; OVEST = 1 ; SUD = 2; EAST= 3
            if(!(gameMap.getRooms()[0][0].getSingleRoom(adiacentPosition).getType().equals(RoomType.ROOM_TYPE))){
                if(adiacentPosition==1){
                    entryRoom.setSingleRoom(adiacentPosition,generateRoom(1,0, gameMap)); //stanza a destra
                }else if(adiacentPosition==2){
                    entryRoom.setSingleRoom(adiacentPosition,generateRoom(0,1, gameMap)); // stanza a sinistra
                }
            }
        }
    }

    private static Room generateRoom(int x,int y,GameMap map){
        Room room=new Room("stanza"+x+" "+y,new HashMap<>(), new HashMap<>(),x,y);
        // se c'è spazio continua
        if(x < (map.getRooms().length-1) && y < (map.getRooms()[0].length-1)){
            int maxAdiacentRooms= (int)Math.floor(Math.random()*5);
            for(int i=0;i<maxAdiacentRooms;i++){
                int adiacentPosition=3-((int)Math.floor(Math.random()*4));//NORD = 0; OVEST = 1 ; SUD = 2; EAST= 3
                switch (adiacentPosition){
                    case 0:{
                        if(gameMap.getRooms().length-1 > 0){
                            if(gameMap.getRooms()[y-1][x] != null){
                                gameMap.getRooms()[y-1][x] = room.setSingleRoom(adiacentPosition,generateRoom(x,y-1, gameMap));
                            }else if(gameMap.getRooms()[y-1][x].getType().equals(RoomType.ROOM_TYPE)){
                                gameMap.getRooms()[y-1][x] = room.setSingleRoom(adiacentPosition,generateRoom(x,y-1, gameMap));
                            }
                        }
                        break;
                    }
                    case 1:{
                        if((gameMap.getRooms()[0].length+1) < (gameMap.getRooms().length-1)){
                            if(gameMap.getRooms()[y][x+1] != null){
                                gameMap.getRooms()[y][x+1] = room.setSingleRoom(adiacentPosition,generateRoom(x+1,y, gameMap));
                            }else if(!(gameMap.getRooms()[y][x+1].getType().equals(RoomType.ROOM_TYPE))){
                                gameMap.getRooms()[y][x+1] = room.setSingleRoom(adiacentPosition,generateRoom(x+1,y, gameMap));
                            }
                        }
                        break;
                    }
                    case 2:{
                        if((gameMap.getRooms().length+1) < (gameMap.getRooms().length-1)){
                            if(gameMap.getRooms()[y+1][x] != null){
                                gameMap.getRooms()[y+1][x] = room.setSingleRoom(adiacentPosition,generateRoom(x,y+1, gameMap));
                            }else if(!(gameMap.getRooms()[y+1][x].getType().equals(RoomType.ROOM_TYPE))){
                                room.setSingleRoom(adiacentPosition,generateRoom(x,y+1, gameMap));
                            }
                        }
                        break;
                    }
                    case 3:{
                        if((gameMap.getRooms()[0].length-1) > 0){
                            if(gameMap.getRooms()[y][x-1] != null){
                                gameMap.getRooms()[y][x-1] = room.setSingleRoom(adiacentPosition,generateRoom(x-1,y, gameMap));
                            }else if(!(gameMap.getRooms()[y][x-1].getType().equals(RoomType.ROOM_TYPE))){
                                gameMap.getRooms()[y][x-1] = room.setSingleRoom(adiacentPosition,generateRoom(x-1,y, gameMap));
                            }
                        }
                        break;
                    }
                }
            }
        }
        return room;
    }
}
