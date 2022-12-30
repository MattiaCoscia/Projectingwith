package pawtropolis.utility.model.generationMethod;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

public abstract class GenerationMethod {
	
	private GameMap map;
	
	protected GenerationMethod(GameMap map) {
		this.map = map;
	}
	
	public abstract GameMap generateMap(Player player);
	
	protected abstract void regulateMapPopulationRooms(Room actualRoom, int startingX, int startingY);
	
	protected abstract void chooseAndAssignAdiacentRooms(Room actualRoom, int maxAdiacentRooms,List<Integer> availablePositions);
	
	protected abstract List<Integer> availableAdiacentPositions(int x, int y);
	
	protected abstract boolean isNearEntry(Room room, int startingX, int startingY);
	
	public abstract boolean isOnTheBorder(Room room);
	
	public int getMapOccupiedSize() {
		List<Room> listRooms = Arrays.stream(map.getRooms())  //'array' is two-dimensional
			    .flatMap(Arrays::stream).filter(r->r!=null)
			    .collect(Collectors.toList());
		return listRooms.size();
	}


	public GameMap getMap() {
		return map;
	}


	public void setMap(GameMap map) {
		this.map = map;
	}
	
	

}
