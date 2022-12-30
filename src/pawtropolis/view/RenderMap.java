package pawtropolis.view;

import java.util.ArrayList;
import java.util.List;

import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomType;

public class RenderMap {

	private static List<String> visibleRooms = new ArrayList<>();

	private static void putVisibleRoom(Room room, List<Integer> directions) {
		for (Integer i : directions) {
			if (room.getAdiacentRooms()[i] != null) {
				visibleRooms.add(
						room.getAdiacentRooms()[i].getPositionY() + ";" + room.getAdiacentRooms()[i].getPositionX());
				putVisibleRoom(room.getAdiacentRooms()[i], List.of(i));
			}
		}
	}

	public static void printMap(GameMap map, Player player) {
		visibleRooms.add(player.getPositionY() + ";"+ player.getPositionX());
		List<Integer> directions = new ArrayList<>();
		int count = 0;
		for (Room r : map.getRooms()[player.getPositionY()][player.getPositionX()].getAdiacentRooms()) {
			if (r != null) {
				directions.add(count);
			}
			count++;
		}
		putVisibleRoom(map.getRooms()[player.getPositionY()][player.getPositionX()], directions);
		for (Room[] line : map.getRooms()) {
			String printLineHead = "";
			String printLineBody = "";
			String printLineFoot = "";
			for (Room room : line) {
				// NORD = 0;
				// OVEST = 3 ; EAST= 1
				// SUD = 2;
				if (room != null && visibleRooms.contains(room.getPositionY() + ";" + room.getPositionX())) {
					String nord = room.getAdiacentRooms()[0] != null ? "|   |" : "+===+";
					String sud = room.getAdiacentRooms()[2] != null ? "|   |" : "+===+";
					String estOvest = "";
					if (room.getAdiacentRooms()[3] != null && room.getAdiacentRooms()[1] != null) {
						estOvest = "   " + room.getChainPosition() + "  ";
					} else if (room.getAdiacentRooms()[3] != null && room.getAdiacentRooms()[1] == null) {
						estOvest = "  " + room.getChainPosition() + "  |";
					} else if (room.getAdiacentRooms()[3] == null && room.getAdiacentRooms()[1] != null) {
						estOvest = "|  " + room.getChainPosition() + "  ";
					} else if (room.getAdiacentRooms()[3] == null && room.getAdiacentRooms()[1] == null) {
						estOvest = "|  " + room.getChainPosition() + " |";
					}
					for (int i = 0; i < (room.getChainPosition() + "").length(); i++) {
						estOvest = estOvest.replaceFirst(" ", "");
					}
					if (room.getType().equals(RoomType.CORRIDOR_TYPE)) {
						nord = nord.replace(' ', ':');
						estOvest = estOvest.replace(' ', ':');
						sud = sud.replace(' ', ':');
					}
					/*
					 * if(room.getName().equals("Entry")) { estOvest=estOvest.replace(' ', 'E'); }
					 */
					printLineHead += nord;
					printLineBody += estOvest;
					printLineFoot += sud;
				} else {
					printLineHead += "     ";
					printLineBody += "     ";
					printLineFoot += "     ";
					// ok
				}
			}
			System.out.println(printLineHead + "\n" + printLineBody + "\n" + printLineFoot);
		}
	}
}
