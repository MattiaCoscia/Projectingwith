package pawtropolis.view;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomType;

@Service
public class RenderMapService {

	@Autowired
	private Player player;
	@Autowired
	private GameMap map;
	private static boolean showMap=false;
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
	
	private List<Integer> chooseDirectionForVisibleRooms() {
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
	public void printMap() {
		if(!showMap) {
			putVisibleRoom(map.getRooms()[player.getPositionY()][player.getPositionX()]
					, chooseDirectionForVisibleRooms());
		}
		List<String> commandsStrings=getListCommandsToPrint();
		List<String> dataAboutRoom=getDataAboutRoom();
		List<String> directionsToPrint=getDirectionsToPrintInCompass(map.getRooms()[player.getPositionY()][player.getPositionX()]);
		for (Room[] line : map.getRooms()) {
			String printLineHead = "";
			String printLineBody = "";
			String printLineFoot = "";
			for (Room room : line) {
				String[] lineToPrint=addRoomPrintToLine(room,printLineHead,printLineBody,printLineFoot);
				printLineHead=lineToPrint[0];
				printLineBody=lineToPrint[1];
				printLineFoot=lineToPrint[2];
			}
			System.out.println(addPanelWithCommandAndInfo(printLineHead, printLineBody, printLineFoot,
					dataAboutRoom, commandsStrings,directionsToPrint));
		}
		System.out.print("Put command: ");
	}

	private String[] addRoomPrintToLine(Room room,String printLineHead,String printLineBody,String printLineFoot){
		if (showMap && room != null || room != null && visibleRooms.contains(room.getPositionY() + ";" + room.getPositionX())
		) {
			String nord = room.getAdiacentRooms()[0] != null ? "|   |" : "+===+";
			String sud = room.getAdiacentRooms()[2] != null ? "|   |" : "+===+";
			String estOvest = printBodyConnectionsRoom(room);
			String[] roomVisuals=addVisualsForCorridorAndPlayerPosition(room,nord,estOvest,sud);
			printLineHead += roomVisuals[0];
			printLineBody += roomVisuals[1];
			printLineFoot += roomVisuals[2];
		} else {
			printLineHead += "     ";
			printLineBody += "     ";
			printLineFoot += "     ";
		}

		return new String[]{printLineHead,printLineBody,printLineFoot};
	}

	private String[] addVisualsForCorridorAndPlayerPosition(Room room,String nord,String estOvest, String sud){
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
		return new String[]{nord,estOvest,sud};
	}
	private String printBodyConnectionsRoom(Room room) {
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
	
	private String addPanelWithCommandAndInfo(String printLineHead, String printLineBody, String printLineFoot,
			List<String> dataAboutRoom, List<String> commandsStrings, List<String> directions) {
		if(!commandsStrings.isEmpty()) {
			String toAdd=commandsStrings.remove(0);
			printLineHead += (" ||"+toAdd);
			printLineBody +=(" ||");
			printLineFoot +=(" ||");
			if(toAdd.length()<18) {
				StringBuilder printLineHeadBuilder = new StringBuilder(printLineHead);
				for(int i = 18-toAdd.length(); i>0; i--) {
					printLineHeadBuilder.append(" ");
				}
				printLineHead = printLineHeadBuilder.toString();
			}
		}
		StringBuilder printLineBodyBuilder = new StringBuilder(printLineBody);
		StringBuilder printLineFootBuilder = new StringBuilder(printLineFoot);
		for(int i = 0; i<18; i++) {
			printLineBodyBuilder.append(" ");
			printLineFootBuilder.append(" ");
		}
		printLineFoot = printLineFootBuilder.toString();
		printLineBody = printLineBodyBuilder.toString();
		if(!dataAboutRoom.isEmpty()){
			String toAdd=dataAboutRoom.remove(0);
			printLineHead +=(" ||"+toAdd);
			printLineBody +=(" ||");
			printLineFoot +=(" ||");
			if(toAdd.length()<21) {
				StringBuilder printLineHeadBuilder = new StringBuilder(printLineHead);
				for(int i = 21-toAdd.length(); i>0; i--) {
					printLineHeadBuilder.append(" ");
				}
				printLineHead = printLineHeadBuilder.toString();
			}
		}
		StringBuilder printLineBodyBuilder1 = new StringBuilder(printLineBody);
		StringBuilder printLineFootBuilder1 = new StringBuilder(printLineFoot);
		for(int i = 0; i<21; i++) {
			printLineBodyBuilder1.append(" ");
			printLineFootBuilder1.append(" ");
		}
		printLineFoot = printLineFootBuilder1.toString();
		printLineBody = printLineBodyBuilder1.toString();
		if(directions != null) {
			if(!directions.isEmpty()) {
				printLineHead +=(" ||"+directions.remove(0));
				if(!directions.isEmpty()) {
					printLineBody +=(" ||"+directions.remove(0));
					printLineFoot +=(" ||"+directions.remove(0));
				}
			}
		}
		return printLineHead + "\n" + printLineBody + "\n" + printLineFoot;
	}
	
	private List<String> getDataAboutRoom(){
		List<String> data = new ArrayList<>();
		data.add("INFO ROOM");
		Room actualRoom = map.getRooms()[player.getPositionY()][player.getPositionX()];
		data.add("Actual Room :" + actualRoom.getName());
		
		data.add("Items in this room:");
		StringBuilder items = new StringBuilder();
		for (String s : actualRoom.getItems().keySet()) {
			items.append(s).append(" x").append(actualRoom.getItems().get(s).size()).append("|");
		}
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
	
	private List<String> getDirectionsToPrintInCompass(Room room){
		List<String> directions = new ArrayList<>();
		directions.add("      DIRECTIONS      ");
		if(room.getAdiacentRooms()[0] !=null) {
			directions.add("      ^ NORTH ^       ");
		}else {
			directions.add("      +=WALL==+       ");
		}
		if (room.getAdiacentRooms()[3] != null && room.getAdiacentRooms()[1] != null) {
			directions.add("WEST <          > EAST");
		} else if (room.getAdiacentRooms()[3] != null && room.getAdiacentRooms()[1] == null) {
			directions.add("WEST <         || WALL");
		} else if (room.getAdiacentRooms()[3] == null && room.getAdiacentRooms()[1] != null) {
			directions.add("WALL||          > EAST");
		} else if (room.getAdiacentRooms()[3] == null && room.getAdiacentRooms()[1] == null) {
			directions.add("WALL||         || WALL");
		}
		if(room.getAdiacentRooms()[2] !=null) {
			directions.add("      v SOUTH v       ");
		}else {
			directions.add("      +=WALL==+       ");
		}
		return directions;
	}

	public void setShowMap(boolean showMap) {
		RenderMapService.showMap = showMap;
	}
}
