package pawtropolis.utility;

import pawtropolis.model.RoomType;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class GameMapGenerator {
    public static final GameMap gameMap=new GameMap(new Room[5][5]);


    public static void generateMap() throws InvocationTargetException, IllegalAccessException {
        gameMap.getRooms()[0][0]=new Room("Entry",new HashMap<>(),new HashMap<>(),0,0);
        Room entryRoom=gameMap.getRooms()[0][0];
        int maxAdiacentRooms= (int)Math.floor(Math.random()*3);
        int minRooms=maxAdiacentRooms > 0 ? maxAdiacentRooms:1;
        for(int i=0;i<minRooms;i++){
            int adiacentPosition=2-((int)Math.floor(Math.random()*2));//NORD = 0; OVEST = 1 ; SUD = 2; EAST= 3
            if(gameMap.getRooms()[0][0].getSingleRoom(adiacentPosition) == null){
                if(adiacentPosition==1){
                    entryRoom.setSingleRoom(adiacentPosition,(generateRoom(1,0, gameMap,0))); //stanza a destra
                }else if(adiacentPosition==2){
                    entryRoom.setSingleRoom(adiacentPosition,(generateRoom(0,1, gameMap,0))); // stanza a sinistra
                }
            }else{
                if(!(gameMap.getRooms()[0][0].getSingleRoom(adiacentPosition).equals(RoomType.ROOM_TYPE))){
                    if(adiacentPosition==1){
                        entryRoom.setSingleRoom(adiacentPosition,(generateRoom(1,0, gameMap,0))); //stanza a destra
                    }else if(adiacentPosition==2){
                        entryRoom.setSingleRoom(adiacentPosition,(generateRoom(0,1, gameMap,0))); // stanza a sinistra
                    }
                }
            }
        }
    }

    private static Room generateRoom(int x,int y,GameMap map,int node) throws InvocationTargetException, IllegalAccessException {
        Method setSingleRoom=null;
        try {
            setSingleRoom=GameMapGenerator.class.getDeclaredMethod("generateRoom", int.class, int.class, GameMap.class, int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Room room=new Room("stanza x"+x+" y"+y,new HashMap<>(), new HashMap<>(),x,y);
        // se c'è spazio continua
        if(x < (map.getRooms().length) && y < (map.getRooms()[0].length)){
            int maxAdiacentRooms= (int)Math.floor(Math.random()*5);
            for(int i=0;i<maxAdiacentRooms;i++){
                int adiacentPosition=3-((int)Math.floor(Math.random()*4));//NORD = 0; OVEST = 1 ; SUD = 2; EAST= 3
                switch (adiacentPosition){
                    case 0:{
                        if(y-1 >= 0){
                            if(gameMap.getRooms()[y-1][x] == null){
                                ManageRecursiveGeneratedMethod manager=new ManageRecursiveGeneratedMethod((node+1)+"",x,y-1,gameMap)
                                        .addRequest(setSingleRoom,(node+1)+"");
                                manager.run();
                                gameMap.getRooms()[y-1][x] =manager.getRoom();
                                        //room.setSingleRoom(adiacentPosition,generateRoom(x,y-1, gameMap,node+1));
                            }else if(gameMap.getRooms()[y-1][x].getType().equals(RoomType.ROOM_TYPE)){
                                ManageRecursiveGeneratedMethod manager=new ManageRecursiveGeneratedMethod((node+1)+"",x,y-1,gameMap)
                                        .addRequest(setSingleRoom,(node+1)+"");
                                manager.run();
                                gameMap.getRooms()[y-1][x] =manager.getRoom();
                                        //room.setSingleRoom(adiacentPosition,generateRoom(x,y-1, gameMap));
                            }
                        }
                    }
                    case 1:{
                        if((x+1) <= (gameMap.getRooms().length-1)){
                            if(gameMap.getRooms()[y][x+1] == null){
                                ManageRecursiveGeneratedMethod manager=new ManageRecursiveGeneratedMethod((node+1)+"",x+1,y,gameMap)
                                        .addRequest(setSingleRoom,(node+1)+"");
                                manager.run();
                                gameMap.getRooms()[y][x+1] =manager.getRoom();
                                        //room.setSingleRoom(adiacentPosition,generateRoom(x+1,y, gameMap,node+1));
                            }else if(!(gameMap.getRooms()[y][x+1].getType().equals(RoomType.ROOM_TYPE))){
                                ManageRecursiveGeneratedMethod manager=new ManageRecursiveGeneratedMethod((node+1)+"",x+1,y,gameMap)
                                        .addRequest(setSingleRoom,(node+1)+"");
                                manager.run();
                                gameMap.getRooms()[y][x+1] =manager.getRoom();
                                        //room.setSingleRoom(adiacentPosition,generateRoom(x+1,y, gameMap,node+1));
                            }
                        }
                    }
                    case 2:{
                        if((y+1) <= (gameMap.getRooms().length-1)){
                            if(gameMap.getRooms()[y+1][x] == null){
                                ManageRecursiveGeneratedMethod manager=new ManageRecursiveGeneratedMethod((node+1)+"",x,y+1,gameMap)
                                        .addRequest(setSingleRoom,(node+1)+"");
                                manager.run();
                                gameMap.getRooms()[y+1][x] =manager.getRoom();
                                        //room.setSingleRoom(adiacentPosition,generateRoom(x,y+1, gameMap,node+1));
                            }else if(!(gameMap.getRooms()[y+1][x].getType().equals(RoomType.ROOM_TYPE))){
                                ManageRecursiveGeneratedMethod manager=new ManageRecursiveGeneratedMethod((node+1)+"",x,y+1,gameMap)
                                        .addRequest(setSingleRoom,(node+1)+"");
                                manager.run();
                                gameMap.getRooms()[y+1][x] =manager.getRoom();
                                        //room.setSingleRoom(adiacentPosition,generateRoom(x,y+1, gameMap,node+1));
                            }
                        }
                    }
                    case 3:{
                        if((x-1) >= 0){
                            if(gameMap.getRooms()[y][x-1] == null){
                                ManageRecursiveGeneratedMethod manager=new ManageRecursiveGeneratedMethod((node+1)+"",x-1,y,gameMap)
                                        .addRequest(setSingleRoom,(node+1)+"");
                                manager.run();
                                gameMap.getRooms()[y][x-1] =manager.getRoom();
                                        //room.setSingleRoom(adiacentPosition,generateRoom(x-1,y, gameMap,node+1));
                            }else if(!(gameMap.getRooms()[y][x-1].getType().equals(RoomType.ROOM_TYPE))){
                                ManageRecursiveGeneratedMethod manager=new ManageRecursiveGeneratedMethod((node+1)+"",x-1,y,gameMap)
                                        .addRequest(setSingleRoom,(node+1)+"");
                                manager.run();
                                gameMap.getRooms()[y][x-1] =manager.getRoom();
                                        //room.setSingleRoom(adiacentPosition,generateRoom(x-1,y, gameMap,node+1));
                            }
                        }
                    }
                }
            }
        }
        return room;
    }
}
