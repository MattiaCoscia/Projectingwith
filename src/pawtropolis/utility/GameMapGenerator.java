package pawtropolis.utility;

import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.util.HashMap;

public class GameMapGenerator {
    public static final GameMap gameMap = new GameMap(new Room[20][20]);

    public static void generateMap() {
        Room entryRoom = new Room("Entry", new HashMap<>(), new HashMap<>(), 0, 0);
        Room firstRoom = new Room("First", new HashMap<>(), new HashMap<>(), 0, 0);
        gameMap.getRooms()[0][0] = entryRoom;
        int adiacentPosition = (int) Math.floor(3 - ((Math.random() * 2+0.01)));//NORD = 0; OVEST = 1 ; SUD = 2; EAST= 3
        if (adiacentPosition == 1) {
            firstRoom.setPositionX(1);
            gameMap.getRooms()[0][1] = firstRoom;
            entryRoom.setSingleRoom(adiacentPosition, firstRoom); //stanza a ovest
            firstRoom.setSingleRoom(3,entryRoom);
        } else if (adiacentPosition == 2) {
            firstRoom.setPositionY(1);
            gameMap.getRooms()[1][0] = firstRoom;
            entryRoom.setSingleRoom(adiacentPosition, firstRoom); // stanza a sud
            firstRoom.setSingleRoom(0,firstRoom);
        }
        int line= firstRoom.getPositionY();
        int column= firstRoom.getPositionX();
        for(int y=line;y<gameMap.getRooms().length;y++){
            for(int x=column;x<gameMap.getRooms()[0].length;x++){
                generateRoom(x,y,gameMap.getRooms()[y][x]);
            }
        }
    }

    private static void generateRoom(int x, int y,Room actualRoom) {
        if(gameMap.getRooms()[y][x] != null){
            int maxAdiacentRooms = (int) Math.floor(Math.random() * 5);
            System.out.println("====================================================================");
            System.out.println(actualRoom.getName());
            for (int i = 0; i < maxAdiacentRooms; i++) {
                int adiacentPosition = 3 - ((int) Math.floor(Math.random() * 4));//NORD = 0; OVEST = 1 ; SUD = 2; EAST= 3
                System.out.println("Position:"+adiacentPosition);
                Room adiacentRoom = new Room("", new HashMap<>(), new HashMap<>(), 0, 0);
                switch (adiacentPosition) {
                    case 0: {
                        if (y - 1 >= 0) {
                            adiacentRoom.setName("^^^");
                            adiacentRoom.setPositionY(y-1);
                            adiacentRoom.setPositionX(x);
                            if (gameMap.getRooms()[y - 1][x] == null) {
                                actualRoom.setSingleRoom(adiacentPosition,adiacentRoom);
                                adiacentRoom.setSingleRoom(2,actualRoom);
                                gameMap.getRooms()[y - 1][x] = adiacentRoom;
                            }
                        }
                        break;
                    }
                    case 1: {
                        if ((x + 1) <= (gameMap.getRooms().length - 1)) {
                            adiacentRoom.setName("==>");
                            adiacentRoom.setPositionY(y);
                            adiacentRoom.setPositionX(x+1);
                            if (gameMap.getRooms()[y][x + 1] == null) {
                                actualRoom.setSingleRoom(adiacentPosition,adiacentRoom);
                                adiacentRoom.setSingleRoom(3,actualRoom);
                                gameMap.getRooms()[y][x + 1] = adiacentRoom;
                            }
                        }
                        break;
                    }
                    case 2: {
                        if ((y + 1) <= (gameMap.getRooms().length - 1)) {
                            adiacentRoom.setName("vvv");
                            adiacentRoom.setPositionY(y+1);
                            adiacentRoom.setPositionX(x);
                            if (gameMap.getRooms()[y + 1][x] == null) {
                                actualRoom.setSingleRoom(adiacentPosition,adiacentRoom);
                                adiacentRoom.setSingleRoom(0,actualRoom);
                                gameMap.getRooms()[y + 1][x] = adiacentRoom;
                            }
                        }
                        break;
                    }
                    case 3: {
                        if ((x - 1) >= 0) {
                            adiacentRoom.setName("<==");
                            adiacentRoom.setPositionY(y);
                            adiacentRoom.setPositionX(x-1);
                            if (gameMap.getRooms()[y][x - 1] == null) {
                                actualRoom.setSingleRoom(adiacentPosition,adiacentRoom);
                                adiacentRoom.setSingleRoom(1,actualRoom);
                                gameMap.getRooms()[y][x - 1] = adiacentRoom;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
}
