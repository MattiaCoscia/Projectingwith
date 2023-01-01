package pawtropolis.utility.model.generationMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomType;

public class RandomChainDisposition{

	private int proximityX = 0;
	private int proximityY = 0;

	private int startingX=0;
	private int startingY=0;
	private final Queue<Room> queueRoomsPositions = new LinkedList<Room>();
	private final Map<Room, Integer> farestRooms = new HashMap<>();
	private GameMap map=null;

	public RandomChainDisposition(GameMap map) {
		this.map=map;
		proximityX = (int) Math.floor(this.map.getRooms()[0].length / 3);
		proximityY = (int) Math.floor(this.map.getRooms().length / 3);
	}

	public GameMap generateMap(Player player) {
		popuplateMapWithStartingRoom(player);
		while (queueRoomsPositions.size() > 0) {
			Room roomInQueue=queueRoomsPositions.poll();
			regulateMapPopulationRooms(roomInQueue);
		}
		chooseWhichRoomsAreCorridorEnd(sortRoomsByChainPosition());
		return this.map;
	}

	private void popuplateMapWithStartingRoom(Player player){
		startingX = (int) Math.floor(this.map.getRooms()[0].length * Math.random());
		startingY = (int) Math.floor(this.map.getRooms().length * Math.random());
		player.setPositionX(startingX);
		player.setPositionY(startingY);
		Room entryRoom = new Room("Entry", new HashMap<>(), new HashMap<>(), startingX, startingY, RoomType.ROOM_TYPE,
				0);
		this.map.setRoom(entryRoom);
		queueRoomsPositions.add(entryRoom);
	}

	protected void regulateMapPopulationRooms(Room actualRoom) {
		List<Integer> availablePositions = availableAdiacentPositions(actualRoom.getPositionX(),
				actualRoom.getPositionY());
		int maxAdiacentRooms = (int) Math.floor(Math.random() * (availablePositions.size() + 1));

		if (isNearEntry(actualRoom) && maxAdiacentRooms < 1) {
			maxAdiacentRooms = availablePositions.size();
		} else if ((availablePositions.size() > 2 && maxAdiacentRooms < 1)) {
			maxAdiacentRooms = availablePositions.size();
		}
		chooseAndAssignAdiacentRooms(actualRoom, maxAdiacentRooms, availablePositions);
	}

	public List<Integer> availableAdiacentPositions(int x, int y) {
		boolean PossiblePositionNord = ((y - 1) >= 0);
		boolean PossiblePositionSud = ((y + 1) < this.map.getRooms().length);
		boolean PossiblePositionEast = ((x + 1) < this.map.getRooms()[0].length);
		boolean PossiblePositionOvest = ((x - 1) >= 0);
		List<Integer> availablePositions = new ArrayList<>();
		if (PossiblePositionNord && (this.map.getRooms()[y - 1][x] == null)) {
			availablePositions.add(0);
		}
		if (PossiblePositionEast && (this.map.getRooms()[y][x + 1] == null)) {
			availablePositions.add(1);
		}
		if (PossiblePositionSud && (this.map.getRooms()[y + 1][x] == null)) {
			availablePositions.add(2);
		}
		if (PossiblePositionOvest && (this.map.getRooms()[y][x - 1] == null)) {
			availablePositions.add(3);
		}
		return availablePositions;
	}

	public void chooseAndAssignAdiacentRooms(Room actualRoom, int maxAdiacentRooms, List<Integer> availablePositions) {
		for (int i = 0; i < maxAdiacentRooms; i++) {
			int randomValuePosition = ((int) Math.floor(Math.random() * availablePositions.size()));
			int adiacentPosition = availablePositions.remove(randomValuePosition).intValue();
			// NORD = 0;
			// OVEST = 3 ; EAST= 1
			// SUD = 2;
			Room adiacentRoom = new Room("", new HashMap<>(), new HashMap<>(), 0, 0, RoomType.ROOM_TYPE,
					actualRoom.getChainPosition() + 1);
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
			this.map.setRoom(adiacentRoom);
			adiacentRoom.setName("Y:" + adiacentRoom.getPositionY() + " X:" + adiacentRoom.getPositionX());
			queueRoomsPositions.add(adiacentRoom);
		}
	}

	private List<Room> sortRoomsByChainPosition() {
		List<Room> collection = Arrays.stream(this.map.getRooms()).flatMap(Arrays::stream).filter(r -> r != null)
				.collect(Collectors.toList());
		collection.sort((r1, r2) -> Integer.compare(-r1.getChainPosition(), -r2.getChainPosition()));
		return collection;
	}

	private void chooseWhichRoomsAreCorridorEnd(List<Room> sortedRooms) {
		int halfList = (int) Math.floor(sortedRooms.size() / 2);
		Room farestRoom = sortedRooms.get(0);
		Room halfDistanceRoom = sortedRooms.get(halfList);
		int randomCorridorRange = (int) (Math.ceil(Math.random() * 2));
		BiPredicate<Integer, Integer> validRoomChain = (a, b) -> (a <= b && a > (b - randomCorridorRange));
		sortedRooms = sortedRooms.stream()
				.filter(r -> (validRoomChain.test(r.getChainPosition(), halfDistanceRoom.getChainPosition()))
						|| (validRoomChain.test(r.getChainPosition(), farestRoom.getChainPosition())))
				.collect(Collectors.toList());
		sortedRooms.forEach(r -> {
			if (isOnTheBorder(r)) {
				recursionToFormCorridorFromEndRoomToStartingRoom(r);
			}
		});
	}

	private void recursionToFormCorridorFromEndRoomToStartingRoom(Room r) {
		r.setType(RoomType.CORRIDOR_TYPE);
		for (Room room : r.getAdiacentRooms()) {
			if (room != null && (!room.getType().equals(RoomType.CORRIDOR_TYPE))
					&& room.getChainPosition() == r.getChainPosition() - 1) {
				recursionToFormCorridorFromEndRoomToStartingRoom(room);
			}
		}
	}

	public boolean isNearEntry(Room room) {
		boolean proximityXToEntry = room.getPositionX() >= (startingX - proximityX)
				&& room.getPositionX() <= (startingX + proximityX);

		boolean proximityYToEntry = room.getPositionY() >= (startingY - proximityY)
				&& room.getPositionY() <= (startingY + proximityY);
		if (proximityXToEntry && proximityYToEntry) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isOnTheBorder(Room room) {
		int maxX = this.map.getRooms()[0].length - 1;
		int maxY = this.map.getRooms().length - 1;
		BiPredicate<Integer, Integer> nordEst = (x, y) -> (x <= (0 + proximityX) && x >= 0)
				&& (y <= (0 + proximityY) && y >= 0);
		BiPredicate<Integer, Integer> nordOvest = (x, y) -> (x <= maxX && x >= (maxX - proximityX))
				&& (y <= (0 + proximityY) && y >= 0);
		BiPredicate<Integer, Integer> sudEst = (x, y) -> (x <= (0 + proximityX) && x >= 0)
				&& (y <= maxY && y >= (maxY - proximityY));
		BiPredicate<Integer, Integer> sudOvest = (x, y) -> (x <= maxX && x >= (maxX - proximityX))
				&& (y <= maxY && y >= (maxY - proximityY));
		if (nordEst.test(room.getPositionX(), room.getPositionY())
				|| nordOvest.test(room.getPositionX(), room.getPositionY())
				|| sudEst.test(room.getPositionX(), room.getPositionY())
				|| sudOvest.test(room.getPositionX(), room.getPositionY())) {
			return true;
		}
		return false;
	}
	
	
	public int getMapOccupiedSize() {
		List<Room> listRooms = Arrays.stream(map.getRooms())  //'array' is two-dimensional
			    .flatMap(Arrays::stream).filter(r->r!=null)
			    .collect(Collectors.toList());
		return listRooms.size();
	}

}
