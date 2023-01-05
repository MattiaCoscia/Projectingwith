package pawtropolis.utility.model.generationMethod;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomType;

public class RandomChainDisposition extends GenerationMethod {

	private int proximityX = 0;
	private int proximityY = 0;

	private Random randomBasedOnSeed=null;
	private int startingX = 0;
	private int startingY = 0;
	private final Queue<Room> queueRoomsPositions = new LinkedList<Room>();
	private GameMap map = null;

	public RandomChainDisposition(GameMap map, long seed) {
		this.map = map;
		this.randomBasedOnSeed=new Random();
		this.randomBasedOnSeed.setSeed(seed);
		proximityX = this.map.getRooms()[0].length / 3;
		proximityY = this.map.getRooms().length / 3;
	}

	@Override
	public GameMap generateMap(Player player, List<Item> items) {
		populateMapWithStartingRoom(player);
		while (!queueRoomsPositions.isEmpty()) {
			Room roomInQueue = queueRoomsPositions.poll();
			regulateMapPopulationRooms(roomInQueue);
		}
		chooseWhichRoomsAreCorridorEnd(sortRoomsByChainPosition());
		for(Room[] y:map.getRooms()){
			for(Room x:y){
				if(x!=null && x.getType().equals(RoomType.ROOM_TYPE)){
					addItemsToRoom(items,x,this.randomBasedOnSeed);
					addNpcsToRoom(x,this.randomBasedOnSeed);
				}
			}
		}
		return this.map;
	}

	private void populateMapWithStartingRoom(Player player) {
		startingX = randomBasedOnSeed.nextInt(0,this.map.getRooms()[0].length);
		startingY = randomBasedOnSeed.nextInt(0,this.map.getRooms().length);
		player.setPositionX(startingX);
		player.setPositionY(startingY);
		Room entryRoom = new Room("Entry", new HashMap<>(), new ArrayList<>(), startingX, startingY, RoomType.ROOM_TYPE,
				0);
		this.map.setRoom(entryRoom);
		queueRoomsPositions.add(entryRoom);
	}

	protected void regulateMapPopulationRooms(Room actualRoom) {
		List<Integer> availablePositions = availableAdiacentPositions(actualRoom.getPositionX(),
				actualRoom.getPositionY());
		int maxAdiacentRooms = randomBasedOnSeed.nextInt(0,availablePositions.size()+1);

		if (isNearEntry(actualRoom) && maxAdiacentRooms < 1 || (availablePositions.size() > 2 && maxAdiacentRooms < 1)) {
			maxAdiacentRooms = availablePositions.size();
		}
		chooseAndAssignAdiacentRooms(actualRoom, maxAdiacentRooms, availablePositions);
	}

	public List<Integer> availableAdiacentPositions(int x, int y) {
		boolean possiblePositionNord = ((y - 1) >= 0);
		boolean possiblePositionSud = ((y + 1) < this.map.getRooms().length);
		boolean possiblePositionEast = ((x + 1) < this.map.getRooms()[0].length);
		boolean possiblePositionOvest = ((x - 1) >= 0);
		List<Integer> availablePositions = new ArrayList<>();
		if (possiblePositionNord && (this.map.getRooms()[y - 1][x] == null)) {
			availablePositions.add(0);
		}
		if (possiblePositionEast && (this.map.getRooms()[y][x + 1] == null)) {
			availablePositions.add(1);
		}
		if (possiblePositionSud && (this.map.getRooms()[y + 1][x] == null)) {
			availablePositions.add(2);
		}
		if (possiblePositionOvest && (this.map.getRooms()[y][x - 1] == null)) {
			availablePositions.add(3);
		}
		return availablePositions;
	}

	public void chooseAndAssignAdiacentRooms(Room actualRoom,int maxAdiacentRooms, List<Integer> availablePositions) {
		for (int i = 0; i < maxAdiacentRooms; i++) {
			int randomValuePosition = randomBasedOnSeed.nextInt(0,availablePositions.size());
			int adiacentPosition = availablePositions.remove(randomValuePosition).intValue();
			Room adiacentRoom = new Room("", new HashMap<>(),new ArrayList<>(), 0, 0, RoomType.ROOM_TYPE,
					actualRoom.getChainPosition() + 1);
			switch (adiacentPosition) {
				case 0 -> {
					adiacentRoom.setPositionY(actualRoom.getPositionY() - 1);
					adiacentRoom.setPositionX(actualRoom.getPositionX());
					actualRoom.setSingleRoom(0, adiacentRoom);
					adiacentRoom.setSingleRoom(2, actualRoom);
				}
				case 1 -> {
					adiacentRoom.setPositionY(actualRoom.getPositionY());
					adiacentRoom.setPositionX(actualRoom.getPositionX() + 1);
					actualRoom.setSingleRoom(1, adiacentRoom);
					adiacentRoom.setSingleRoom(3, actualRoom);
				}
				case 2 -> {
					adiacentRoom.setPositionY(actualRoom.getPositionY() + 1);
					adiacentRoom.setPositionX(actualRoom.getPositionX());
					actualRoom.setSingleRoom(2, adiacentRoom);
					adiacentRoom.setSingleRoom(0, actualRoom);
				}
				case 3 -> {
					adiacentRoom.setPositionY(actualRoom.getPositionY());
					adiacentRoom.setPositionX(actualRoom.getPositionX() - 1);
					actualRoom.setSingleRoom(3, adiacentRoom);
					adiacentRoom.setSingleRoom(1, actualRoom);
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
		int halfList = sortedRooms.size() / 2;
		Room farestRoom = sortedRooms.get(0);
		Room halfDistanceRoom = sortedRooms.get(halfList);
		int randomCorridorRange = randomBasedOnSeed.nextInt(1,3);
		BiPredicate<Integer, Integer> validRoomChain = (a, b) -> (a <= b && a > (b - randomCorridorRange));
		sortedRooms = sortedRooms.stream()
				.filter(r -> (validRoomChain.test(r.getChainPosition(), halfDistanceRoom.getChainPosition()))
						|| (validRoomChain.test(r.getChainPosition(), farestRoom.getChainPosition()))).toList();
		sortedRooms.forEach(r -> {
			if (isOnTheBorder(r)) {
				recursionToFormCorridorFromEndRoomToStartingRoom(r, halfDistanceRoom.getChainPosition());
			}
		});
	}

	private void recursionToFormCorridorFromEndRoomToStartingRoom(Room r, int halfLenght) {
		r.setType(RoomType.CORRIDOR_TYPE);
		for (Room room : r.getAdiacentRooms()) {
			if (room != null && (!room.getType().equals(RoomType.CORRIDOR_TYPE))
					&& room.getChainPosition() == r.getChainPosition() - 1) {
				recursionToFormCorridorFromEndRoomToStartingRoom(room, halfLenght);
			}
		}
	}

	public boolean isNearEntry(Room room) {
		boolean proximityXToEntry = room.getPositionX() >= (startingX - proximityX)
				&& room.getPositionX() <= (startingX + proximityX);

		boolean proximityYToEntry = room.getPositionY() >= (startingY - proximityY)
				&& room.getPositionY() <= (startingY + proximityY);
		return proximityXToEntry && proximityYToEntry;
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
		if(nordEst.test(room.getPositionX(), room.getPositionY())
				|| nordOvest.test(room.getPositionX(), room.getPositionY())
				|| sudEst.test(room.getPositionX(), room.getPositionY())
				|| sudOvest.test(room.getPositionX(), room.getPositionY())) {
			return true;
		}
		return false;
	}
}
