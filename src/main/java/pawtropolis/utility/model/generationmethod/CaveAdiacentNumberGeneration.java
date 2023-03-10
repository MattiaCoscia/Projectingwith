package pawtropolis.utility.model.generationmethod;

import lombok.extern.slf4j.Slf4j;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomType;

import java.util.*;

@Slf4j
public class CaveAdiacentNumberGeneration extends GenerationMethod {

    private int[][] numberMap = null;
    private GameMap map = null;
    private Random randomBasedOnSeed = null;
    private final Queue<String> queueNumberPositions = new LinkedList<>();

    public CaveAdiacentNumberGeneration(GameMap map, long seed) {
        this.numberMap = new int[map.getRooms().length][map.getRooms()[0].length];
        this.randomBasedOnSeed = new Random(seed);
        this.map = map;
    }

    @Override
    public GameMap generateMap(Player player, List<Item> items) {
        populateIntegerMapWithStartingRoom(player);
        while (!queueNumberPositions.isEmpty()) {
            String[] yx = queueNumberPositions.poll().split(";");
            int x = Integer.parseInt(yx[1]);
            int y = Integer.parseInt(yx[0]);
            chooseAndAssignRooms(x, y, availableAdiacentPositions(x, y));
        }
        convertIntegerMapToGameMap(items);
        for (Room[] y : map.getRooms()) {
            for (Room r : y) {
                if (r != null) {
                    chooseAndAssignAdiacentRooms(r);
                }
            }
        }
        return map;
    }

    public void chooseAndAssignRooms(int x, int y, List<Integer> availablePositions) {
        int actualValue = numberMap[y][x];
        for (int adiacentPosition : availablePositions) {
            if (areaValue(x, y) <= 7) {
                switch (adiacentPosition) {
                    case 0: {
                        if ((areaValue(x, y - 1) % 2) == 0) {
                            numberMap[y - 1][x] = (actualValue == 1) ? 2 : 1;
                        } else {
                            numberMap[y - 1][x] = (actualValue == 1) ? 1 : 2;
                        }
                        queueNumberPositions.add((y - 1) + ";" + x + ";");
                        break;
                    }
                    case 1: {
                        if ((areaValue(x + 1, y) % 2) == 0) {
                            numberMap[y][x + 1] = (actualValue == 1) ? 2 : 1;
                        } else {
                            numberMap[y][x + 1] = (actualValue == 1) ? 1 : 2;
                        }
                        queueNumberPositions.add(y + ";" + (x + 1) + ";");
                        break;
                    }
                    case 2: {
                        if ((areaValue(x, y + 1) % 2) == 0) {
                            numberMap[y + 1][x] = (actualValue == 1) ? 2 : 1;
                        } else {
                            numberMap[y + 1][x] = (actualValue == 1) ? 1 : 2;
                        }
                        queueNumberPositions.add((y + 1) + ";" + x + ";");
                        break;
                    }
                    case 3: {
                        if ((areaValue(x - 1, y) % 2) == 0) {
                            numberMap[y][x - 1] = (actualValue == 1) ? 2 : 1;
                        } else {
                            numberMap[y][x - 1] = (actualValue == 1) ? 1 : 2;
                        }
                        queueNumberPositions.add(y + ";" + (x - 1) + ";");
                        break;
                    }
                    default: {
                        System.out.println("Error to assign a room!");
                    }
                }
            }
        }
    }

    private void populateIntegerMapWithStartingRoom(Player player) {
        int randomX = randomBasedOnSeed.nextInt(0, numberMap[0].length);
        int randomY = randomBasedOnSeed.nextInt(0, numberMap.length);
        player.setPositionX(randomX);
        player.setPositionY(randomY);
        numberMap[randomY][randomX] = randomBasedOnSeed.nextInt(1, 3);
        queueNumberPositions.add(randomY + ";" + randomX + ";");
    }

    private GameMap convertIntegerMapToGameMap(List<Item> items) {
        int countY = 0;
        int countX = 0;
        for (Room[] y : map.getRooms()) {
            countX = 0;
            for (Room x : y) {
                int room = numberMap[countY][countX];
                if (room == 1) {
                    map.getRooms()[countY][countX] = new Room("Y:" + countY + " X:" + countX, new HashMap<>(), new ArrayList<>(), countX,
                            countY, RoomType.CORRIDOR_TYPE, room);
                } else if (room == 2) {
                    map.getRooms()[countY][countX] = new Room("Y:" + countY + " X:" + countX, new HashMap<>(), new ArrayList<>(), countX,
                            countY, RoomType.ROOM_TYPE, room);
                    addItemsToRoom(items, map.getRooms()[countY][countX], randomBasedOnSeed);
                    addNpcsToRoom(map.getRooms()[countY][countX], randomBasedOnSeed);
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

    private int nullArea(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((y + i >= 0 && y + i < numberMap.length) && (x + j >= 0 && x + j < numberMap[0].length) && numberMap[y + i][x + j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public void chooseAndAssignAdiacentRooms(Room room) {
        if (room != null) {
            List<Integer> positions = new ArrayList<>(List.of(0, 1, 2, 3));
            boolean possiblePositionNord = ((room.getPositionY() - 1) >= 0);
            boolean possiblePositionSud = ((room.getPositionY() + 1) < numberMap.length);
            boolean possiblePositionEast = ((room.getPositionX() + 1) < numberMap[0].length);
            boolean possiblePositionOvest = ((room.getPositionX() - 1) >= 0);
            Room adiacentRoom = null;
            for (Room possibleAdiacentRoom : room.getAdiacentRooms()) {
                adiacentRoom = null;
                if (possibleAdiacentRoom == null) {
                    int listPosition = randomBasedOnSeed.nextInt(0, positions.size());
                    int countRoom = positions.remove(listPosition);
                    switch (countRoom) {
                        case 0: {
                            if (possiblePositionNord) {
                                adiacentRoom = map.getRooms()[room.getPositionY() - 1][room.getPositionX()];
                            }
                            break;
                        }
                        case 1: {
                            if (possiblePositionEast) {
                                adiacentRoom = map.getRooms()[room.getPositionY()][room.getPositionX() + 1];
                            }
                            break;
                        }
                        case 2: {
                            if (possiblePositionSud) {
                                adiacentRoom = map.getRooms()[room.getPositionY() + 1][room.getPositionX()];
                            }
                            break;
                        }
                        case 3: {
                            if (possiblePositionOvest) {
                                adiacentRoom = map.getRooms()[room.getPositionY()][room.getPositionX() - 1];
                            }
                            break;
                        }
                        default: {
                            log.error("Error to verify a free position for assign an adjacent room!");
                        }
                    }
                    if (adiacentRoom != null && conditionsToLinkRooms(room, adiacentRoom, room.getPositionX(), room.getPositionY())) {
                        room.setSingleRoom(countRoom, adiacentRoom);
                        int nextAdiacentRoom = countRoom+2 < 4 ? countRoom+2 : countRoom-2;
                        adiacentRoom.setSingleRoom(nextAdiacentRoom, room);
                    }
                }
            }
        }
    }

    private boolean conditionsToLinkRooms(Room room, Room adiacentRoom, int actualRoomX, int actualRoomY) {
        return (room.getType().equals(adiacentRoom.getType()) && randomBasedOnSeed.nextInt(10) > 4
                || (noConnectionToDifferentTypeRecursion(room, new ArrayList<>()) <= 1
                || nullArea(actualRoomX, actualRoomY) >= 1));
    }

    private int noConnectionToDifferentTypeRecursion(Room room, List<Room> alreadyChecked) {
        int neighbourWithForeignNeighbour = 0;
        List<Room> checkedRooms = new ArrayList<>();
        checkedRooms.addAll(alreadyChecked);
        for (Room r : room.getAdiacentRooms()) {
            if (r != null) {
                if (r.getType().equals(room.getType()) && !(checkedRooms.stream().anyMatch(r2 -> r2.equals(r)))) {
                    checkedRooms.add(r);
                    neighbourWithForeignNeighbour += noConnectionToDifferentTypeRecursion(r, checkedRooms);
                    return neighbourWithForeignNeighbour;
                } else if (!(r.getType().equals(room.getType()))) {
                    neighbourWithForeignNeighbour++;
                }
            }
        }

        return neighbourWithForeignNeighbour;
    }

    public List<Integer> availableAdiacentPositions(int x, int y) {
        boolean possiblePositionNord = ((y - 1) >= 0);
        boolean possiblePositionSud = ((y + 1) < numberMap.length);
        boolean possiblePositionEast = ((x + 1) < numberMap[0].length);
        boolean possiblePositionOvest = ((x - 1) >= 0);
        List<Integer> availablePositions = new ArrayList<>();
        if (possiblePositionNord && (numberMap[y - 1][x] == 0)) {
            availablePositions.add(0);
        }
        if (possiblePositionEast && (numberMap[y][x + 1] == 0)) {
            availablePositions.add(1);
        }
        if (possiblePositionSud && (numberMap[y + 1][x] == 0)) {
            availablePositions.add(2);
        }
        if (possiblePositionOvest && (numberMap[y][x - 1] == 0)) {
            availablePositions.add(3);
        }
        return availablePositions;
    }
}
