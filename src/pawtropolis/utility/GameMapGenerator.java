package pawtropolis.utility;

import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GameMapGenerator {
    private static int dimY = 10;
    private static int dimX = 100;
    public static final GameMap gameMap = new GameMap(new Room[dimY][dimX]);


    //Generatore della mappa
    public static GameMap generateMap() {
        Room entryRoom = new Room("Entry", new HashMap<>(), new HashMap<>(), 0, 0);
        Room firstRoom = new Room("First", new HashMap<>(), new HashMap<>(), 0, 0);
        gameMap.getRooms()[0][0] = entryRoom;
        int adiacentPosition = (int) Math.floor(3 - ((Math.random() * 2 + 0.01)));
        //NORD = 0
        // OVEST = 1      EAST= 3
        // SUD = 2
        if (adiacentPosition == 1) {
            firstRoom.setPositionX(1);
            gameMap.getRooms()[0][1] = firstRoom;
            entryRoom.setSingleRoom(adiacentPosition, firstRoom); //stanza a ovest
            firstRoom.setSingleRoom(3, entryRoom);
        } else if (adiacentPosition == 2) {
            firstRoom.setPositionY(1);
            gameMap.getRooms()[1][0] = firstRoom;
            entryRoom.setSingleRoom(adiacentPosition, firstRoom); // stanza a sud
            firstRoom.setSingleRoom(0, firstRoom);
        }
        int line = firstRoom.getPositionY();
        int column = firstRoom.getPositionX();
        for (int y = line; y < gameMap.getRooms().length; y++) {
            for (int x = column; x < gameMap.getRooms()[0].length; x++) {
                generateRoom(x, y, gameMap.getRooms()[y][x]);
            }
        }
        return gameMap;
    }

    public static int getMapOccupiedSize() {
        List<Room> listRooms = new ArrayList<>();
        for (Room[] lineRooms : gameMap.getRooms()) {
            for (Room roomInLine : lineRooms) {
                if (roomInLine != null) {
                    listRooms.add(roomInLine);
                }
            }
        }
        return listRooms.size();
    }


    private static List<Integer> availableAdiacentPosition(int x,int y,Room actualRoom){
        Room nord= y-1 >= 0 ? gameMap.getRooms()[y-1][x] : null;
        Room sud = y+1 < gameMap.getRooms().length ? gameMap.getRooms()[y+1][x] : null;
        Room east= x+1 < gameMap.getRooms()[0].length ? gameMap.getRooms()[y][x+1] : null;
        Room ovest= x-1 >= 0 ? gameMap.getRooms()[y][x-1] : null;
        List<Integer> availablePositions=new ArrayList<>();
        if(nord!=null){
            availablePositions.add(0);
        }
        if(east!=null){
            availablePositions.add(1);
        }
        if(sud!=null){
            availablePositions.add(2);
        }
        if(ovest!=null){
            availablePositions.add(3);
        }
        return availablePositions;
    }
    //Generatore delle stanze
    private static void generateRoom(int x, int y, Room actualRoom) {
        if (gameMap.getRooms()[y][x] != null) {
            List<Integer> availablePosition=availableAdiacentPosition(x,y,actualRoom);
            int maxAdiacentRooms = (int) Math.floor(Math.random() * (availablePosition.size()+1));
            maxAdiacentRooms = (x == 0 && y == 1 || x == 1 && y == 0) && (maxAdiacentRooms < 1) ? 1 : maxAdiacentRooms;
            for (int i = 0; i < maxAdiacentRooms; i++) {
                int randomValuePosition=((int) Math.floor(Math.random() * availablePosition.size()));
                int adiacentPosition = availablePosition.remove(randomValuePosition);
                //NORD = 0;
                // OVEST = 3 ;          EAST= 1
                // SUD = 2;
                Room adiacentRoom = new Room("", new HashMap<>(), new HashMap<>(), 0, 0);
                switch (adiacentPosition) {
                    case 0: {
                        if (y - 1 >= 0) {
                            adiacentRoom.setPositionY(y - 1);
                            adiacentRoom.setPositionX(x);
                            adiacentRoom.setName("X:" + adiacentRoom.getPositionX() + " Y:" + adiacentRoom.getPositionY());
                            if (gameMap.getRooms()[y - 1][x] == null) {
                                actualRoom.setSingleRoom(adiacentPosition, adiacentRoom);
                                adiacentRoom.setSingleRoom(2, actualRoom);
                                gameMap.getRooms()[y - 1][x] = adiacentRoom;
                            } else {
                                roomReroll(2, x, y, actualRoom, adiacentRoom);
                            }
                        }
                        break;
                    }
                    case 1: {
                        if ((x + 1) <= (gameMap.getRooms().length - 1)) {
                            adiacentRoom.setPositionY(y);
                            adiacentRoom.setPositionX(x + 1);
                            adiacentRoom.setName("X:" + adiacentRoom.getPositionX() + " Y:" + adiacentRoom.getPositionY());
                            if (gameMap.getRooms()[y][x + 1] == null) {
                                actualRoom.setSingleRoom(adiacentPosition, adiacentRoom);
                                adiacentRoom.setSingleRoom(3, actualRoom);
                                gameMap.getRooms()[y][x + 1] = adiacentRoom;
                            } else {
                                roomReroll(2, x, y, actualRoom, adiacentRoom);
                            }
                        }
                        break;
                    }
                    case 2: {
                        if ((y + 1) <= (gameMap.getRooms().length - 1)) {
                            adiacentRoom.setPositionY(y + 1);
                            adiacentRoom.setPositionX(x);
                            adiacentRoom.setName("X:" + adiacentRoom.getPositionX() + " Y:" + adiacentRoom.getPositionY());
                            if (gameMap.getRooms()[y + 1][x] == null) {
                                actualRoom.setSingleRoom(adiacentPosition, adiacentRoom);
                                adiacentRoom.setSingleRoom(0, actualRoom);
                                gameMap.getRooms()[y + 1][x] = adiacentRoom;
                            } else {
                                roomReroll(2, x, y, actualRoom, adiacentRoom);
                            }
                        }
                        break;
                    }
                    case 3: {
                        if ((x - 1) >= 0) {
                            adiacentRoom.setPositionY(y);
                            adiacentRoom.setPositionX(x - 1);
                            adiacentRoom.setName("X:" + adiacentRoom.getPositionX() + " Y:" + adiacentRoom.getPositionY());
                            if (gameMap.getRooms()[y][x - 1] == null) {
                                actualRoom.setSingleRoom(adiacentPosition, adiacentRoom);
                                adiacentRoom.setSingleRoom(1, actualRoom);
                                gameMap.getRooms()[y][x - 1] = adiacentRoom;
                            } else {
                                roomReroll(2, x, y, actualRoom, adiacentRoom);
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private static void roomReroll(int attempts, int x, int y, Room actualRoom, Room adiacentRoom) {
        int adiacentPosition = 3 - ((int) Math.floor(Math.random() * 4));
        if (attempts >= 0) {
            switch (adiacentPosition) {
                case 0: {
                    if (y - 1 >= 0) {
                        if (gameMap.getRooms()[y - 1][x] != null) {
                            roomReroll(attempts - 1, x, y, actualRoom, adiacentRoom);
                        } else {
                            actualRoom.setSingleRoom(adiacentPosition, adiacentRoom);
                            adiacentRoom.setSingleRoom(2, actualRoom);
                            gameMap.getRooms()[y - 1][x] = adiacentRoom;
                        }
                    }
                    break;
                }
                case 1: {
                    if ((x + 1) <= (gameMap.getRooms().length - 1)) {
                        if (gameMap.getRooms()[y][x + 1] != null) {
                            roomReroll(attempts - 1, x, y, actualRoom, adiacentRoom);
                        } else {
                            actualRoom.setSingleRoom(adiacentPosition, adiacentRoom);
                            adiacentRoom.setSingleRoom(3, actualRoom);
                            gameMap.getRooms()[y][x + 1] = adiacentRoom;
                        }
                    }
                    break;
                }
                case 2: {
                    if ((y + 1) <= (gameMap.getRooms().length - 1)) {
                        if (gameMap.getRooms()[y + 1][x] != null) {
                            roomReroll(attempts - 1, x, y, actualRoom, adiacentRoom);
                        } else {
                            actualRoom.setSingleRoom(adiacentPosition, adiacentRoom);
                            adiacentRoom.setSingleRoom(0, actualRoom);
                            gameMap.getRooms()[y + 1][x] = adiacentRoom;
                        }
                    }
                    break;
                }
                case 3: {
                    if ((x - 1) >= 0) {
                        if (gameMap.getRooms()[y][x - 1] != null) {
                            roomReroll(attempts - 1, x, y, actualRoom, adiacentRoom);
                        } else {
                            actualRoom.setSingleRoom(adiacentPosition, adiacentRoom);
                            adiacentRoom.setSingleRoom(1, actualRoom);
                            gameMap.getRooms()[y][x - 1] = adiacentRoom;
                        }
                    }
                    break;
                }
            }
        }
    }
}
