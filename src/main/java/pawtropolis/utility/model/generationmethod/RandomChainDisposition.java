package pawtropolis.utility.model.generationmethod;

import java.util.*;
import java.util.function.BiPredicate;

import lombok.extern.slf4j.Slf4j;
import pawtropolis.utility.RoomNameKeyGenerator;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Inventory;
import pawtropolis.model.items.ItemStored;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomType;
@Slf4j
public class RandomChainDisposition extends GenerationMethod {

	private int proximityX = 0;
	private int proximityY = 0;

	private Random randomBasedOnSeed=null;
	private int startingX = 0;
	private int startingY = 0;
	private final Queue<Room> queueRoomsPositions = new LinkedList<>();
	private GameMap map = null;

	public RandomChainDisposition(GameMap map, long seed) {
		this.map = map;
		this.randomBasedOnSeed=new Random();
		this.randomBasedOnSeed.setSeed(seed);
		proximityX = this.map.getWidthMap() / 3;
		proximityY = this.map.getHeightMap() / 3;
	}

	@Override
	public GameMap generateMap(Player player, List<ItemStored> itemStoreds) {
		populateMapWithStartingRoom(player);
		while (!queueRoomsPositions.isEmpty()) {
			Room roomInQueue = queueRoomsPositions.poll();
			regulateMapPopulationRooms(roomInQueue);
		}
		chooseWhichRoomsAreCorridorEnd(sortRoomsByChainPosition());
		map.getRooms().forEach((key,room) ->{
			if(room.getType().equals(RoomType.ROOM_TYPE)){
				addItemsToRoom(itemStoreds,room,this.randomBasedOnSeed);
				addNpcsToRoom(room,this.randomBasedOnSeed);
			}
		});
		return this.map;
	}

	private void populateMapWithStartingRoom(Player player) {
		startingX = randomBasedOnSeed.nextInt(0,this.map.getWidthMap());
		startingY = randomBasedOnSeed.nextInt(0,this.map.getHeightMap());
		player.setPositionX(startingX);
		player.setPositionY(startingY);
		Room entryRoom = new Room(RoomNameKeyGenerator.giveKeyForRoom(startingY,startingX), new Inventory(), new ArrayList<>(), startingX, startingY, RoomType.ROOM_TYPE,
				0);
		this.map.setSingleRoom(entryRoom);
		queueRoomsPositions.add(entryRoom);
	}

	protected void
	regulateMapPopulationRooms(Room actualRoom) {
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
		boolean possiblePositionSud = ((y + 1) < this.map.getHeightMap());
		boolean possiblePositionEast = ((x + 1) < this.map.getWidthMap());
		boolean possiblePositionOvest = ((x - 1) >= 0);
		List<Integer> availablePositions = new ArrayList<>();
		if (possiblePositionNord && (this.map.getRooms().get(RoomNameKeyGenerator.giveKeyForRoom(y - 1,x)) == null)) {
			availablePositions.add(0);
		}
		if (possiblePositionEast && (this.map.getRooms().get(RoomNameKeyGenerator.giveKeyForRoom(y,x + 1)) == null)) {
			availablePositions.add(1);
		}
		if (possiblePositionSud && (this.map.getRooms().get(RoomNameKeyGenerator.giveKeyForRoom(y + 1,x)) == null)) {
			availablePositions.add(2);
		}
		if (possiblePositionOvest && (this.map.getRooms().get(RoomNameKeyGenerator.giveKeyForRoom(y,x - 1)) == null)) {
			availablePositions.add(3);
		}
		return availablePositions;
	}

	public void chooseAndAssignAdiacentRooms(Room actualRoom,int maxAdiacentRooms, List<Integer> availablePositions) {
		if(!availablePositions.isEmpty()){
			for (int i = 0; i < maxAdiacentRooms; i++) {
				int randomValuePosition = randomBasedOnSeed.nextInt(0,availablePositions.size());
				int adiacentPosition = availablePositions.remove(randomValuePosition);
				Room adiacentRoom = new Room("", new Inventory(),new ArrayList<>(), 0, 0, RoomType.ROOM_TYPE,
						actualRoom.getChainPosition() + 1);
				switch (adiacentPosition) {
					case 0 -> {
						adiacentRoom.setPositionY(actualRoom.getPositionY() - 1);
						adiacentRoom.setPositionX(actualRoom.getPositionX());
					}
					case 1 -> {
						adiacentRoom.setPositionY(actualRoom.getPositionY());
						adiacentRoom.setPositionX(actualRoom.getPositionX() + 1);
					}
					case 2 -> {
						adiacentRoom.setPositionY(actualRoom.getPositionY() + 1);
						adiacentRoom.setPositionX(actualRoom.getPositionX());
					}
					case 3 -> {
						adiacentRoom.setPositionY(actualRoom.getPositionY());
						adiacentRoom.setPositionX(actualRoom.getPositionX() - 1);
					}
				}
				adiacentRoom.setName(RoomNameKeyGenerator.giveKeyForRoom(adiacentRoom.getPositionY(),adiacentRoom.getPositionX()));
				actualRoom.setSingleRoom(DirectionEnum.values()[adiacentPosition],adiacentRoom);
				this.map.setSingleRoom(adiacentRoom);
				queueRoomsPositions.add(adiacentRoom);
			}
		}
	}

	private List<Room> sortRoomsByChainPosition() {
		List<Room> collection = new ArrayList<>(this.map.getRooms().values());
		collection.sort(Comparator.comparingInt(r -> -r.getChainPosition()));
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
		for (Room room : r.getAdiacentRooms().values()) {
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
		int maxX = this.map.getWidthMap() - 1;
		int maxY = this.map.getHeightMap() - 1;
		boolean flag= false;
		BiPredicate<Integer, Integer> nordEst = (x, y) -> (x <= (proximityX) && x >= 0)
				&& (y <= (proximityY) && y >= 0);
		BiPredicate<Integer, Integer> nordOvest = (x, y) -> (x <= maxX && x >= (maxX - proximityX))
				&& (y <= (proximityY) && y >= 0);
		BiPredicate<Integer, Integer> sudEst = (x, y) -> (x <= (proximityX) && x >= 0)
				&& (y <= maxY && y >= (maxY - proximityY));
		BiPredicate<Integer, Integer> sudOvest = (x, y) -> (x <= maxX && x >= (maxX - proximityX))
				&& (y <= maxY && y >= (maxY - proximityY));
		if(nordEst.test(room.getPositionX(), room.getPositionY())
				|| nordOvest.test(room.getPositionX(), room.getPositionY())
				|| sudEst.test(room.getPositionX(), room.getPositionY())
				|| sudOvest.test(room.getPositionX(), room.getPositionY())) {
			flag= true;
		}
		return flag;
	}
}
