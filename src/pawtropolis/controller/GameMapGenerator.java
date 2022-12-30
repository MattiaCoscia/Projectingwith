package pawtropolis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomType;

public class GameMapGenerator {
	private static int dimY = 10;
	private static int dimX = 10;
	private static int proximityXtoEntry = (int) Math.floor(dimX / 3);
	private static int proximityYtoEntry = (int) Math.floor(dimY / 3);
	private static final GameMap gameMap = new GameMap(new Room[dimY][dimX]);
	private static final Queue<Room> queueRoomsPositions = new LinkedList<Room>();
	private static final Map<Room, Integer> farestRooms = new HashMap<>();

	// Generatore della mappa
	public static GameMap generateMap() {
		int startingX = (int) Math.floor(dimX * Math.random());
		int startingY = (int) Math.floor(dimY * Math.random());
		Room entryRoom = new Room("Entry", new HashMap<>(), new HashMap<>(), startingX, startingY, RoomType.ROOM_TYPE);
		gameMap.setRoom(entryRoom);
		queueRoomsPositions.add(entryRoom);
		// NORD = 0
		// OVEST = 3 EAST= 1
		// SUD = 2
		while (queueRoomsPositions.size() > 0) {
			populateMap(queueRoomsPositions.poll(), startingX, startingY);
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

	private static void populateMap(Room actualRoom, int startingX, int startingY) {
		if (actualRoom != null) {
			List<Integer> availablePosition = availableAdiacentPosition(actualRoom.getPositionX(),actualRoom.getPositionY());
			int maxAdiacentRooms = (int) Math.floor(Math.random() * (availablePosition.size() + 1));
			
			if (isNearEntry(actualRoom, startingX, startingY) && maxAdiacentRooms < 1) {
				maxAdiacentRooms = availablePosition.size();
			} else if ((availablePosition.size() > 2 && maxAdiacentRooms < 1)
					|| (actualRoom.getName().equals("Entry") && maxAdiacentRooms < 1)) {
				
				maxAdiacentRooms = availablePosition.size();
			}
			chooseAndAssignAdiacentRooms(actualRoom, maxAdiacentRooms, availablePosition);
		}
	}

	private static void chooseAndAssignAdiacentRooms(Room actualRoom, int maxAdiacentRooms,
			List<Integer> availablePosition) {
		for (int i = 0; i < maxAdiacentRooms; i++) {
			int randomValuePosition = ((int) Math.floor(Math.random() * availablePosition.size()));
			int adiacentPosition = availablePosition.remove(randomValuePosition).intValue();
			// NORD = 0;
			// OVEST = 3 ; EAST= 1
			// SUD = 2;
			Room adiacentRoom = new Room("", new HashMap<>(), new HashMap<>(), 0, 0, RoomType.ROOM_TYPE);
			switch (adiacentPosition) {
			case 0: {
				adiacentRoom.setPositionY(actualRoom.getPositionY() - 1);
				adiacentRoom.setPositionX(actualRoom.getPositionX());
				actualRoom.setSingleRoom(0, adiacentRoom);
				adiacentRoom.setSingleRoom(2, actualRoom);
				break;
			}
			case 1: {
				adiacentRoom.setPositionY(actualRoom.getPositionY());
				adiacentRoom.setPositionX(actualRoom.getPositionX() + 1);
				actualRoom.setSingleRoom(1, adiacentRoom);
				adiacentRoom.setSingleRoom(3, actualRoom);
				break;
			}
			case 2: {
				adiacentRoom.setPositionY(actualRoom.getPositionY() + 1);
				adiacentRoom.setPositionX(actualRoom.getPositionX());
				actualRoom.setSingleRoom(2, adiacentRoom);
				adiacentRoom.setSingleRoom(0, actualRoom);
				break;
			}
			case 3: {
				adiacentRoom.setPositionY(actualRoom.getPositionY());
				adiacentRoom.setPositionX(actualRoom.getPositionX() - 1);
				actualRoom.setSingleRoom(3, adiacentRoom);
				adiacentRoom.setSingleRoom(1, actualRoom);
				break;
			}
			}
			gameMap.setRoom(adiacentRoom);
			adiacentRoom.setName("Y:" + adiacentRoom.getPositionY() + " X:" + adiacentRoom.getPositionX());
			queueRoomsPositions.add(adiacentRoom);
		}
	}

	public static boolean isNearEntry(Room room, int startingX, int startingY) {
		boolean proximityX = room.getPositionX() >= (startingX - proximityXtoEntry)
				&& room.getPositionX() <= (startingX + proximityXtoEntry);

		boolean proximityY = room.getPositionY() >= (startingY - proximityYtoEntry)
				&& room.getPositionY() <= (startingY + proximityYtoEntry);
		if(proximityX && proximityY) {
			return true;
		}else {
			return false;
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
