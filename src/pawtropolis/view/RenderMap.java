package pawtropolis.view;

import java.util.ArrayList;
import java.util.List;

import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomType;

public class RenderMap {

	private static List<String> visibleRooms = new ArrayList<>();

	private static void putVisibleRoom(Room room, List<Integer> directions) {
		for (Integer i : directions) {
			if (room.getAdiacentRooms()[i] != null) {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				visibleRooms.add(
						room.getAdiacentRooms()[i].getPositionY() + ";" + room.getAdiacentRooms()[i].getPositionX());
				putVisibleRoom(room.getAdiacentRooms()[i], list);
			}
		}
	}
	
	private static List<Integer> chooseDirectionForVisibleRooms(GameMap map, Player player) {
		visibleRooms = new ArrayList<>();
		visibleRooms.add(player.getPositionY() + ";" + player.getPositionX());
		List<Integer> directions = new ArrayList<>();
		int count = 0;
		for (Room r : map.getRooms()[player.getPositionY()][player.getPositionX()].getAdiacentRooms()) {
			if (r != null) {
				directions.add(count);
			}
			count++;
		}
		return directions;
	}

	public static void printMap(GameMap map, Player player) {
		putVisibleRoom(map.getRooms()[player.getPositionY()][player.getPositionX()]
				, chooseDirectionForVisibleRooms(map, player));
		List<String> commandsStrings=getListCommandsToPrint();
		List<String> dataAboutRoom=getDataAboutRoom(player, map);
		for (Room[] line : map.getRooms()) {
			String printLineHead = "";
			String printLineBody = "";
			String printLineFoot = "";
			for (Room room : line) {
				if (room != null && visibleRooms.stream()
						.anyMatch(r -> r.equals(room.getPositionY() + ";" + room.getPositionX()))) {
					String nord = room.getAdiacentRooms()[0] != null ? "|   |" : "+===+";
					String sud = room.getAdiacentRooms()[2] != null ? "|   |" : "+===+";
					String estOvest = printBodyConnectionsRoom(room);
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
					printLineHead += nord;
					printLineBody += estOvest;
					printLineFoot += sud;
				} else {
					printLineHead += "     ";
					printLineBody += "     ";
					printLineFoot += "     ";
				}
			}
			System.out.println(addPanelWithCommandAndInfo(printLineHead, printLineBody, printLineFoot,
					dataAboutRoom, commandsStrings));
		}
		System.out.print("Put command: ");
	}
	
	private static String printBodyConnectionsRoom(Room room) {
		String estOvest = "";
		if (room.getAdiacentRooms()[3] != null && room.getAdiacentRooms()[1] != null) {
			estOvest = "  .  ";
		} else if (room.getAdiacentRooms()[3] != null && room.getAdiacentRooms()[1] == null) {
			estOvest = "  . |";
		} else if (room.getAdiacentRooms()[3] == null && room.getAdiacentRooms()[1] != null) {
			estOvest = "| .  ";
		} else if (room.getAdiacentRooms()[3] == null && room.getAdiacentRooms()[1] == null) {
			estOvest = "| . |";
		}
		return estOvest;
	}
	
	private static String addPanelWithCommandAndInfo(String printLineHead, String printLineBody, String printLineFoot,
			List<String> dataAboutRoom, List<String> commandsStrings) {
		if(commandsStrings.size() > 0) {
			String toAdd=commandsStrings.remove(0);
			printLineHead += (" ||"+toAdd);
			printLineBody +=(" ||");
			printLineFoot +=(" ||");
			if(toAdd.length()<18) {
				for(int i=18-toAdd.length();i>0;i--) {
					printLineHead+=" ";
				}
			}
		}
		for(int i=0;i<18;i++) {
			printLineBody +=(" ");
			printLineFoot +=(" ");
		}
		if(dataAboutRoom.size() > 0){
			printLineHead +=(" ||"+dataAboutRoom.remove(0));
			printLineBody +=(" ||");
			printLineFoot +=(" ||");
		}
		return printLineHead + "\n" + printLineBody + "\n" + printLineFoot;
	}
	
	private static List<String> getDataAboutRoom(Player player, GameMap map){
		List<String> data = new ArrayList<>();
		data.add("INFO ROOM");
		Room actualRoom = map.getRooms()[player.getPositionY()][player.getPositionX()];
		data.add("Actual Room :" + actualRoom.getName());
		
		data.add("Items in this room:");
		String items = "";
		for (String s : actualRoom.getItems().keySet()) {
			items += s + " x" + actualRoom.getItems().get(s).size() + " | ";
		}
		data.add(items);
		
		data.add("Npcs in this room:");
		String npcs = "";
		for (Entity s : actualRoom.getEntities()) {
			npcs += s.getName() + " | ";
		}
		data.add(npcs);
		
		return data;
	}

	private static List<String> getListCommandsToPrint() {
		List<String> commands = new ArrayList<>();
		commands.add("COMMANDS");
		commands.add("'go <direction>'");
		commands.add("'get <item name>'");
		commands.add("'drop <item name>'");
		commands.add("'look'");
		commands.add("'bag'");
		return commands;
	}
}
