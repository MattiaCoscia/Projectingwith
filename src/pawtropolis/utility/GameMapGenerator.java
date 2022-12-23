package pawtropolis.utility;

import pawtropolis.model.RoomType;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.util.HashMap;

public class GameMapGenerator {
    public static final GameMap gameMap = new GameMap(new Room[5][5]);


    public static void generateMap() {
        Room entryRoom = new Room("Entry", new HashMap<>(), new HashMap<>(), 0, 0);
        Room firstRoom = new Room("First", new HashMap<>(), new HashMap<>(), 0, 0);
        gameMap.getRooms()[0][0] = entryRoom;
        int adiacentPosition = (int) Math.floor(2 - (Math.random() * 2));//NORD = 0; OVEST = 1 ; SUD = 2; EAST= 3
        if (adiacentPosition == 1) {
            firstRoom.setPositionX(1);
            gameMap.getRooms()[0][1] = firstRoom;
            entryRoom.setSingleRoom(adiacentPosition, firstRoom); //stanza a ovest
        } else if (adiacentPosition == 2) {
            firstRoom.setPositionY(1);
            gameMap.getRooms()[1][0] = firstRoom;
            entryRoom.setSingleRoom(adiacentPosition, firstRoom); // stanza a sud
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
            for (int i = 0; i < maxAdiacentRooms; i++) {
                int adiacentPosition = 3 - ((int) Math.floor(Math.random() * 4));//NORD = 0; OVEST = 1 ; SUD = 2; EAST= 3
                Room adiacentRoom = new Room("", new HashMap<>(), new HashMap<>(), 0, 0);
                switch (adiacentPosition) {
                    case 0: {
                        if (y - 1 >= 0) {
                            adiacentRoom.setName(x+(y-1)+"");
                            adiacentRoom.setPositionY(y-1);
                            adiacentRoom.setPositionX(x);
                            if (gameMap.getRooms()[y - 1][x] == null) {
                                gameMap.getRooms()[y - 1][x] = adiacentRoom;
                            } else if (gameMap.getRooms()[y - 1][x].getType().equals(RoomType.ROOM_TYPE)) {
                                gameMap.getRooms()[y - 1][x] = adiacentRoom;
                            }
                        }
                    }
                    case 1: {
                        if ((x + 1) <= (gameMap.getRooms().length - 1)) {
                            adiacentRoom.setName((x+1)+y+"");
                            adiacentRoom.setPositionY(y);
                            adiacentRoom.setPositionX(x+1);
                            if (gameMap.getRooms()[y][x + 1] == null) {
                                gameMap.getRooms()[y][x + 1] = adiacentRoom;
                            } else if (!(gameMap.getRooms()[y][x + 1].getType().equals(RoomType.ROOM_TYPE))) {
                                gameMap.getRooms()[y][x + 1] = adiacentRoom;
                            }
                        }
                    }
                    case 2: {
                        if ((y + 1) <= (gameMap.getRooms().length - 1)) {
                            adiacentRoom.setName(x+(y+1)+"");
                            adiacentRoom.setPositionY(y+1);
                            adiacentRoom.setPositionX(x);
                            if (gameMap.getRooms()[y + 1][x] == null) {
                                gameMap.getRooms()[y + 1][x] = adiacentRoom;
                            } else if (!(gameMap.getRooms()[y + 1][x].getType().equals(RoomType.ROOM_TYPE))) {
                                gameMap.getRooms()[y + 1][x] = adiacentRoom;
                            }
                        }
                    }
                    case 3: {
                        if ((x - 1) >= 0) {
                            adiacentRoom.setName((x-1)+y+"");
                            adiacentRoom.setPositionY(y);
                            adiacentRoom.setPositionX(x-1);
                            if (gameMap.getRooms()[y][x - 1] == null) {
                                gameMap.getRooms()[y][x - 1] = adiacentRoom;
                            } else if (!(gameMap.getRooms()[y][x - 1].getType().equals(RoomType.ROOM_TYPE))) {
                                gameMap.getRooms()[y][x - 1] = adiacentRoom;
                            }
                        }
                    }

                    actualRoom.setSingleRoom(adiacentPosition,adiacentRoom);
                }
            }
        }
    }
}
