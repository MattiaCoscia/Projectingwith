package pawtropolis.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.Door;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RenderMapService {

    private Player player;
    private GameMap map;
    private boolean showMap = false;
    private List<String> visibleRooms = new ArrayList<>();
    private Room[][] roomsMatrix = null;
    @Autowired
    public RenderMapService(GameMap map,Player player){
        this.map = map;
        this.player = player;
    }

    private void putVisibleRoom(Room room, List<Integer> directions) {
        for (Integer i : directions) {
            Door doorInDirection = room.getAdiacentDoors().get(DirectionEnum.values()[i]);
            if (doorInDirection != null) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                Room roomToPrint = doorInDirection.getRoomA() != room ? doorInDirection.getRoomA() : doorInDirection.getRoomB();
                visibleRooms.add(roomToPrint.getPositionY() + ";" + roomToPrint.getPositionX());
                putVisibleRoom(roomToPrint, list);
            }
        }
    }

    private List<Integer> chooseDirectionForVisibleRooms() {
        visibleRooms = new ArrayList<>();
        visibleRooms.add(player.getPositionY() + ";" + player.getPositionX());
        List<Integer> directions = new ArrayList<>();
        int count = 0;
        Room playerRoom = roomsMatrix[player.getPositionY()][player.getPositionX()];
        for (DirectionEnum directionEnum : DirectionEnum.values()) {
            Door doorInDirection = playerRoom.getAdiacentDoors().get(directionEnum);
            if (doorInDirection != null) {
                directions.add(count);
            }
            count++;
        }
        return directions;
    }

    public void printMap() {
        roomsMatrix = new Room[map.getHeightMap()][map.getWidthMap()];
        map.getRooms().forEach((key,room) ->{
            roomsMatrix[room.getPositionY()][room.getPositionX()] = room;
        });
        if (!showMap) {
            putVisibleRoom(roomsMatrix[player.getPositionY()][player.getPositionX()]
                    , chooseDirectionForVisibleRooms());
        }
        List<String> commandsStrings = getListCommandsToPrint();
        List<String> dataAboutRoom = getDataAboutRoom();
        List<String> directionsToPrint = getDirectionsToPrintInCompass(roomsMatrix[player.getPositionY()][player.getPositionX()]);
        for (Room[] line : roomsMatrix) {
            String printLineHead = "";
            String printLineBody = "";
            String printLineFoot = "";
            for (Room room : line) {
                String[] lineToPrint = addRoomPrintToLine(room, printLineHead, printLineBody, printLineFoot);
                printLineHead = lineToPrint[0];
                printLineBody = lineToPrint[1];
                printLineFoot = lineToPrint[2];
            }
            System.out.println(addPanelWithCommandAndInfo(printLineHead, printLineBody, printLineFoot,
                    dataAboutRoom, commandsStrings, directionsToPrint));
        }
        log.info("Put command: ");
    }

    private String[] addRoomPrintToLine(Room room, String printLineHead, String printLineBody, String printLineFoot) {
        if (showMap && room != null || room != null && visibleRooms.contains(room.getPositionY() + ";" + room.getPositionX())
        ) {
            String nord = room.getAdiacentDoors().get(DirectionEnum.NORTH) != null ? "|   |" : "+===+";
            String sud = room.getAdiacentDoors().get(DirectionEnum.SOUTH) != null ? "|   |" : "+===+";
            String estOvest = printBodyConnectionsRoom(room);
            String[] roomVisuals = addVisualsForCorridorAndPlayerPosition(room, nord, estOvest, sud);
            printLineHead += roomVisuals[0];
            printLineBody += roomVisuals[1];
            printLineFoot += roomVisuals[2];
        } else {
            printLineHead += "#####";
            printLineBody += "#####";
            printLineFoot += "#####";
        }

        return new String[]{printLineHead, printLineBody, printLineFoot};
    }

    private String[] addVisualsForCorridorAndPlayerPosition(Room room, String nord, String estOvest, String sud) {
        if (room.getPositionX() == player.getPositionX() && room.getPositionY() == player.getPositionY()) {
            for (int i = 0; i < (room.getChainPosition() + "").length(); i++) {
                estOvest = estOvest.replace('.', '0');
            }
        } else {
            estOvest = estOvest.replace('.', ' ');
        }
        if (room.getType().equals(RoomType.CORRIDOR_TYPE)) {
            nord = nord.replace(' ', ':');
            estOvest = estOvest.replace(' ', ':');
            sud = sud.replace(' ', ':');
        }
        return new String[]{nord, estOvest, sud};
    }

    private String printBodyConnectionsRoom(Room room) {
        String estOvest = "";
        if (room.getAdiacentDoors().get(DirectionEnum.WEST) != null && room.getAdiacentDoors().get(DirectionEnum.EAST) != null) {
            estOvest = "  .  ";
        } else if (room.getAdiacentDoors().get(DirectionEnum.WEST) != null && room.getAdiacentDoors().get(DirectionEnum.EAST) == null) {
            estOvest = "  . |";
        } else if (room.getAdiacentDoors().get(DirectionEnum.WEST) == null && room.getAdiacentDoors().get(DirectionEnum.EAST) != null) {
            estOvest = "| .  ";
        } else if (room.getAdiacentDoors().get(DirectionEnum.WEST) == null && room.getAdiacentDoors().get(DirectionEnum.EAST) == null) {
            estOvest = "| . |";
        }
        return estOvest;
    }

    private String addPanelWithCommandAndInfo(String printLineHead, String printLineBody, String printLineFoot,
                                              List<String> dataAboutRoom, List<String> commandsStrings, List<String> directions) {
        if (!commandsStrings.isEmpty()) {
            String toAdd = commandsStrings.remove(0);
            printLineHead += (" ||" + toAdd);
            printLineBody += (" ||");
            printLineFoot += (" ||");
            if (toAdd.length() < 18) {
                StringBuilder printLineHeadBuilder = new StringBuilder(printLineHead);
                for (int i = 18 - toAdd.length(); i > 0; i--) {
                    printLineHeadBuilder.append(" ");
                }
                printLineHead = printLineHeadBuilder.toString();
            }
        }
        StringBuilder printLineBodyBuilder = new StringBuilder(printLineBody);
        StringBuilder printLineFootBuilder = new StringBuilder(printLineFoot);
        for (int i = 0; i < 18; i++) {
            printLineBodyBuilder.append(" ");
            printLineFootBuilder.append(" ");
        }
        printLineFoot = printLineFootBuilder.toString();
        printLineBody = printLineBodyBuilder.toString();
        if (!dataAboutRoom.isEmpty()) {
            String toAdd = dataAboutRoom.remove(0);
            printLineHead += (" ||" + toAdd);
            printLineBody += (" ||");
            printLineFoot += (" ||");
            if (toAdd.length() < 21) {
                StringBuilder printLineHeadBuilder = new StringBuilder(printLineHead);
                for (int i = 21 - toAdd.length(); i > 0; i--) {
                    printLineHeadBuilder.append(" ");
                }
                printLineHead = printLineHeadBuilder.toString();
            }
        }
        StringBuilder printLineBodyBuilder1 = new StringBuilder(printLineBody);
        StringBuilder printLineFootBuilder1 = new StringBuilder(printLineFoot);
        for (int i = 0; i < 21; i++) {
            printLineBodyBuilder1.append(" ");
            printLineFootBuilder1.append(" ");
        }
        printLineFoot = printLineFootBuilder1.toString();
        printLineBody = printLineBodyBuilder1.toString();
        if (!ObjectUtils.isEmpty(directions)) {
            printLineHead += (" ||" + directions.remove(0));
            if (!directions.isEmpty()) {
                printLineBody += (" ||" + directions.remove(0));
                printLineFoot += (" ||" + directions.remove(0));
            }

        }
        return printLineHead + "\n" + printLineBody + "\n" + printLineFoot;
    }

    private List<String> getDataAboutRoom() {
        List<String> data = new ArrayList<>();
        data.add("INFO ROOM");
        Room actualRoom = roomsMatrix[player.getPositionY()][player.getPositionX()];
        data.add("Actual Room :" + actualRoom.getName());

        data.add("Items in this room:");
        StringBuilder items = new StringBuilder();
        List<String> nameItems = new ArrayList<>(actualRoom.getInventory().getItems().keySet());
        nameItems.forEach(s -> items.append(s).append(" x").append(actualRoom.getItem(s).getQuantity()).append("|"));
        data.add(items.toString());

        data.add("Npcs in this room:");
        StringBuilder npcs = new StringBuilder();
        for (Entity s : actualRoom.getEntities()) {
            npcs.append(s.getName()).append("|");
        }
        data.add(npcs.toString());

        return data;
    }

    private List<String> getListCommandsToPrint() {
        List<String> commands = new ArrayList<>();
        commands.add("COMMANDS");
        commands.add("'go <direction>'");
        commands.add("'get <item name>'");
        commands.add("'drop <item name>'");
        commands.add("'look'");
        commands.add("'bag'");
        return commands;
    }

    private List<String> getDirectionsToPrintInCompass(Room room) {
        List<String> directions = new ArrayList<>();
        directions.add("      DIRECTIONS      ");
        if (room.getAdiacentDoors().get(DirectionEnum.NORTH) != null) {
            directions.add("      ^ NORTH ^       ");
        } else {
            directions.add("      +=WALL==+       ");
        }
        if (room.getAdiacentDoors().get(DirectionEnum.WEST) != null && room.getAdiacentDoors().get(DirectionEnum.EAST) != null) {
            directions.add("WEST <          > EAST");
        } else if (room.getAdiacentDoors().get(DirectionEnum.WEST) != null && room.getAdiacentDoors().get(DirectionEnum.EAST) == null) {
            directions.add("WEST <         || WALL");
        } else if (room.getAdiacentDoors().get(DirectionEnum.WEST) == null && room.getAdiacentDoors().get(DirectionEnum.EAST) != null) {
            directions.add("WALL||          > EAST");
        } else if (room.getAdiacentDoors().get(DirectionEnum.WEST) == null && room.getAdiacentDoors().get(DirectionEnum.EAST) == null) {
            directions.add("WALL||         || WALL");
        }
        if (room.getAdiacentDoors().get(DirectionEnum.SOUTH) != null) {
            directions.add("      v SOUTH v       ");
        } else {
            directions.add("      +=WALL==+       ");
        }
        return directions;
    }

    public void setShowMap(boolean showMap) {
        this.showMap = showMap;
    }
}
