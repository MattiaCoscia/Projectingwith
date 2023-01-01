package pawtropolis.utility.model.generationMethod;

import java.util.*;
import java.util.stream.Collectors;

import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomType;

public class AdiacentNumberGeneration {

    private int[][] numberMap = null;
    private GameMap map=null;
    private final Queue<String> queueNumberPositions = new LinkedList<>();

    public AdiacentNumberGeneration(GameMap map, Player player) {
        this.numberMap = new int[map.getRooms().length][map.getRooms()[0].length];
        this.map=map;
        populateIntegerMap(player);
    }

    public GameMap generateMap(){
        while (queueNumberPositions.size() > 0) {
            String[] yx = queueNumberPositions.poll().split(";");
            int x = Integer.parseInt(yx[1]);
            int y = Integer.parseInt(yx[0]);
            chooseAndAssignRooms(x, y, availableAdiacentPositions(x, y));
        }
        convertIntegerMapToGameMap();
        for (Room[] y : map.getRooms()) {
            for (Room r : y) {
                checkAdiacentRoomAndAssign(r);
            }
        }
        return map;
    }

    public void chooseAndAssignRooms(int x, int y, List<Integer> availablePositions) {
        int actualValue = numberMap[y][x];
        String positionNewCell = "";
        // NORD = 0;
        // OVEST = 3 ; EAST= 1
        // SUD = 2;
        for (int adiacentPosition : availablePositions) {
            switch (adiacentPosition) {
                case 0: {
					if(areaValue(x,y-1) > 10){
						numberMap[y - 1][x]=0;
					}else if ((areaValue(x, y - 1) % 2) == 0) {
                        numberMap[y - 1][x] = (actualValue == 1) ? 2 : 1;
                    } else {
                        numberMap[y - 1][x] = (actualValue == 1) ? 1 : 2;
                    }
                    queueNumberPositions.add((y - 1) + ";" + x + ";");
                    break;
                }
                case 1: {
					if(areaValue(x + 1,y) > 10){
						numberMap[y][x + 1]=0;
					}else if ((areaValue(x + 1, y) % 2) == 0) {
                        numberMap[y][x + 1] = (actualValue == 1) ? 2 : 1;
                    } else {
                        numberMap[y][x + 1] = (actualValue == 1) ? 1 : 2;
                    }
                    queueNumberPositions.add(y + ";" + (x + 1) + ";");
                    break;
                }
                case 2: {
					if(areaValue(x,y + 1) > 10){
						numberMap[y + 1][x]=0;
					}else if ((areaValue(x, y + 1) % 2) == 0) {
                        numberMap[y + 1][x] = (actualValue == 1) ? 2 : 1;
                    } else {
                        numberMap[y + 1][x] = (actualValue == 1) ? 1 : 2;
                    }
                    queueNumberPositions.add((y + 1) + ";" + x + ";");
                    break;
                }
                case 3: {
					if(areaValue(x - 1,y) > 10){
						numberMap[y][x - 1]=0;
					}else if ((areaValue(x - 1, y) % 2) == 0) {
                        numberMap[y][x - 1] = (actualValue == 1) ? 2 : 1;
                    } else {
                        numberMap[y][x - 1] = (actualValue == 1) ? 1 : 2;
                    }
                    queueNumberPositions.add(y + ";" + (x - 1) + ";");
                    break;
                }
            }
        }
    }

    private void populateIntegerMap(Player player) {
        for (int i = 0; i < 4; i++) {
            int randomX = (int) Math.floor(Math.random() * numberMap[0].length);
            int randomY = (int) Math.floor(Math.random() * numberMap[0].length);
            if(i == 0){
                player.setPositionX(randomX);
                player.setPositionY(randomY);
            }
            numberMap[randomY][randomX] = 1;
            queueNumberPositions.add(randomY + ";" + randomX + ";");
        }
    }

    private GameMap convertIntegerMapToGameMap() {
        int countY = 0;
        int countX = 0;
        for (Room[] y : map.getRooms()) {
            countX = 0;
            for (Room x : y) {
                int room = numberMap[countY][countX];
                if (room == 1) {
                    map.getRooms()[countY][countX] = new Room("Y:" + countY + " X:" + countX, null, null, countX,
                            countY, RoomType.CORRIDOR_TYPE, room);
                } else if (room == 2) {
                    map.getRooms()[countY][countX] = new Room("Y:" + countY + " X:" + countX, null, null, countX,
                            countY, RoomType.ROOM_TYPE, room);
                }
                countX++;
            }
            countY++;
        }
        return map;
    }

    private int areaValue(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((y + i >= 0 && y + i < numberMap.length) && (x + j >= 0 && x + j < numberMap[0].length)) {
                    count += numberMap[y + i][x + j];
                }
            }
        }
        return count;
    }

    public void checkAdiacentRoomAndAssign(Room room) {
        if(room != null){
            List<Integer> positions= Arrays.asList(0,1,2,3);
            boolean PossiblePositionNord = ((room.getPositionY() - 1) >= 0);
            boolean PossiblePositionSud = ((room.getPositionY() + 1) < numberMap.length);
            boolean PossiblePositionEast = ((room.getPositionX() + 1) < numberMap[0].length);
            boolean PossiblePositionOvest = ((room.getPositionX() - 1) >= 0);
            int actualRoomX = room.getPositionX();
            int actualRoomY = room.getPositionY();
            Room adiacentRoom = null;
            for (Room r : room.getAdiacentRooms()) {
                int listPosition = (int) Math.floor(Math.random() * positions.size());
                int countRoom = positions.get(listPosition).intValue();
                positions = positions.stream().filter(n-> n.intValue() != countRoom).collect(Collectors.toList());
                switch (countRoom) {
                    case 0: {
                        if (PossiblePositionNord) {
                            adiacentRoom = map.getRooms()[actualRoomY - 1][actualRoomX];
                            if (adiacentRoom != null && room.getType().equals(adiacentRoom.getType())) {
                                r = adiacentRoom;
                                room.setSingleRoom(countRoom, r);
                                r.setSingleRoom(2, room);
                            } else if (adiacentRoom != null && !(room.getType().equals(adiacentRoom.getType()))
                                    && roomHasNoEqualNeighbor(room)) {
                                r = adiacentRoom;
                                room.setSingleRoom(countRoom, r);
                                r.setSingleRoom(2, room);
                            }
                        }
                        break;
                    }
                    case 1: {
                        if (PossiblePositionEast) {
                            adiacentRoom = map.getRooms()[actualRoomY][actualRoomX + 1];
                            if (adiacentRoom != null && room.getType().equals(adiacentRoom.getType())) {
                                r = adiacentRoom;
                                room.setSingleRoom(countRoom, r);
                                r.setSingleRoom(3, room);
                            } else if (adiacentRoom != null && !(room.getType().equals(adiacentRoom.getType()))
                                    && roomHasNoEqualNeighbor(room)) {
                                r = adiacentRoom;
                                room.setSingleRoom(countRoom, r);
                                r.setSingleRoom(3, room);
                            }
                        }
                        break;
                    }
                    case 2: {
                        if (PossiblePositionSud) {
                            adiacentRoom = map.getRooms()[actualRoomY + 1][actualRoomX];
                            if (adiacentRoom != null && room.getType().equals(adiacentRoom.getType())) {
                                r = adiacentRoom;
                                room.setSingleRoom(countRoom, r);
                                r.setSingleRoom(0, room);
                            } else if (adiacentRoom != null && !(room.getType().equals(adiacentRoom.getType()))
                                    && roomHasNoEqualNeighbor(room)) {
                                r = adiacentRoom;
                                room.setSingleRoom(countRoom, r);
                                r.setSingleRoom(0, room);
                            }
                        }
                        break;
                    }
                    case 3: {
                        if (PossiblePositionOvest) {
                            adiacentRoom = map.getRooms()[actualRoomY][actualRoomX - 1];
                            if (adiacentRoom != null && room.getType().equals(adiacentRoom.getType())) {
                                r = adiacentRoom;
                                room.setSingleRoom(countRoom, r);
                                r.setSingleRoom(1, room);
                            } else if (adiacentRoom != null && !(room.getType().equals(adiacentRoom.getType()))
                                    && roomHasNoEqualNeighbor(room)) {
                                r = adiacentRoom;
                                room.setSingleRoom(countRoom, r);
                                r.setSingleRoom(1, room);
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private boolean roomHasNoEqualNeighbor(Room room) {
        int countEqualNeighbor = 0;
        int countAdiacentAssignedNeighbout = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((room.getPositionY() + i >= 0 && room.getPositionY() + i < map.getRooms().length)
                        && (room.getPositionX() + j >= 0 && room.getPositionX() + j < map.getRooms()[0].length)
                && (i != 0 && j != 0) && (i != -1 && j != -1) && (i != -1 && j != 1) && (i != 1 && j != 1) && (i != 1 && j != -1)) {
                    if (map.getRooms()[room.getPositionY() + i][room.getPositionX() + j].getType()
                            .equals(room.getType())) {
                        countEqualNeighbor++;
                    }
                }
            }
        }
        for (Room r : room.getAdiacentRooms()) {
            if (r != null) {
                countAdiacentAssignedNeighbout++;
            }
        }
        if (countEqualNeighbor <= 1 && countAdiacentAssignedNeighbout > 1) {
            return false;
        }
        return countEqualNeighbor <= 2;
    }

    public List<Integer> availableAdiacentPositions(int x, int y) {
        boolean PossiblePositionNord = ((y - 1) >= 0);
        boolean PossiblePositionSud = ((y + 1) < numberMap.length);
        boolean PossiblePositionEast = ((x + 1) < numberMap[0].length);
        boolean PossiblePositionOvest = ((x - 1) >= 0);
        List<Integer> availablePositions = new ArrayList<>();
        if (PossiblePositionNord && (numberMap[y - 1][x] == 0)) {
            availablePositions.add(0);
        }
        if (PossiblePositionEast && (numberMap[y][x + 1] == 0)) {
            availablePositions.add(1);
        }
        if (PossiblePositionSud && (numberMap[y + 1][x] == 0)) {
            availablePositions.add(2);
        }
        if (PossiblePositionOvest && (numberMap[y][x - 1] == 0)) {
            availablePositions.add(3);
        }
        return availablePositions;
    }
}
