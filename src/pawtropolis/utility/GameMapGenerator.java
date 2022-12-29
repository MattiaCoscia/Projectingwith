package pawtropolis.utility;

import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class GameMapGenerator {
    private static int dimY = 50;
    private static int dimX = 100;
    public static final GameMap gameMap = new GameMap(new Room[dimY][dimX]);
    private static final Queue<Room> queueRoomsPositions= new LinkedList<Room>();


    //Generatore della mappa
    public static GameMap generateMap() {
        Room entryRoom = new Room("Entry", new HashMap<>(), new HashMap<>(), 0, 0, RoomType.ROOM_TYPE);
        gameMap.getRooms()[0][0] = entryRoom;
        queueRoomsPositions.add(entryRoom);
        //NORD = 0
        // OVEST = 3      EAST= 1
        // SUD = 2
        while(queueRoomsPositions.size() > 0) {
        	populateMap(queueRoomsPositions.poll());
        }
        return gameMap;
    }

    private static List<Integer> availableAdiacentPosition(int x, int y) {
        boolean PossiblePositionNord = ((y - 1) >= 0);
        boolean PossiblePositionSud = ((y + 1) < gameMap.getRooms().length);
        boolean PossiblePositionEast = ((x + 1) < gameMap.getRooms()[0].length);
        boolean PossiblePositionOvest = ((x - 1) >= 0);
        List<Integer> availablePositions = new ArrayList<>();
        if (PossiblePositionNord && (gameMap.getRooms()[y - 1][x] == null)) {
            availablePositions.add(0);
        }
        if (PossiblePositionEast && (gameMap.getRooms()[y][x + 1] == null)) {
            availablePositions.add(1);
        }
        if (PossiblePositionSud && (gameMap.getRooms()[y + 1][x] == null)) {
            availablePositions.add(2);
        }
        if (PossiblePositionOvest && (gameMap.getRooms()[y][x - 1] == null)) {
            availablePositions.add(3);
        }
        return availablePositions;
    }

    private static void populateMap(Room actualRoom) {
        if (actualRoom != null) {
            List<Integer> availablePosition = availableAdiacentPosition(actualRoom.getPositionX(), actualRoom.getPositionY());
            int maxAdiacentRooms = (int) Math.floor(Math.random() * (availablePosition.size() + 1));
            if (actualRoom.getPositionX() == 0 && actualRoom.getPositionY() == 0 && maxAdiacentRooms < 1) {
                maxAdiacentRooms = 1;
            } else if (availablePosition.size() > 2 && maxAdiacentRooms < 1) {
                maxAdiacentRooms = availablePosition.size();
            }
            chooseAndAssignAdiacentRooms(actualRoom, maxAdiacentRooms, availablePosition);
        }
    }

    private static void chooseAndAssignAdiacentRooms(Room actualRoom, int maxAdiacentRooms, List<Integer> availablePosition) {
        for (int i = 0; i < maxAdiacentRooms; i++) {
            int randomValuePosition = ((int) Math.floor(Math.random() * availablePosition.size()));
            int adiacentPosition = availablePosition.remove(randomValuePosition).intValue();
            //NORD = 0;
            // OVEST = 3 ;          EAST= 1
            // SUD = 2;
            Room adiacentRoom = new Room("", new HashMap<>(), new HashMap<>(), 0, 0, RoomType.ROOM_TYPE);
            switch (adiacentPosition) {
                case 0: {
                    adiacentRoom.setPositionY(actualRoom.getPositionY() - 1);
                    adiacentRoom.setPositionX(actualRoom.getPositionX());
                    adiacentRoom.setName("Y:" + adiacentRoom.getPositionY() + " X:" + adiacentRoom.getPositionX());
                    actualRoom.setSingleRoom(0, adiacentRoom);
                    adiacentRoom.setSingleRoom(2, actualRoom);
                    gameMap.getRooms()[actualRoom.getPositionY() - 1][actualRoom.getPositionX()] = adiacentRoom;
                    break;
                }
                case 1: {
                    adiacentRoom.setPositionY(actualRoom.getPositionY());
                    adiacentRoom.setPositionX(actualRoom.getPositionX() + 1);
                    adiacentRoom.setName("Y:" + adiacentRoom.getPositionY() + " X:" + adiacentRoom.getPositionX());
                    actualRoom.setSingleRoom(1, adiacentRoom);
                    adiacentRoom.setSingleRoom(3, actualRoom);
                    gameMap.getRooms()[actualRoom.getPositionY()][actualRoom.getPositionX() + 1] = adiacentRoom;
                    break;
                }
                case 2: {
                    adiacentRoom.setPositionY(actualRoom.getPositionY() + 1);
                    adiacentRoom.setPositionX(actualRoom.getPositionX());
                    adiacentRoom.setName("Y:" + adiacentRoom.getPositionY() + " X:" + adiacentRoom.getPositionX());
                    actualRoom.setSingleRoom(2, adiacentRoom);
                    adiacentRoom.setSingleRoom(0, actualRoom);
                    gameMap.getRooms()[actualRoom.getPositionY() + 1][actualRoom.getPositionX()] = adiacentRoom;
                    break;
                }
                case 3: {
                    adiacentRoom.setPositionY(actualRoom.getPositionY());
                    adiacentRoom.setPositionX(actualRoom.getPositionX() - 1);
                    adiacentRoom.setName("Y:" + adiacentRoom.getPositionY() + " X:" + adiacentRoom.getPositionX());
                    actualRoom.setSingleRoom(3, adiacentRoom);
                    adiacentRoom.setSingleRoom(1, actualRoom);
                    gameMap.getRooms()[actualRoom.getPositionY()][actualRoom.getPositionX() - 1] = adiacentRoom;
                    break;
                }
            }
            queueRoomsPositions.add(adiacentRoom);
        }
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
}
