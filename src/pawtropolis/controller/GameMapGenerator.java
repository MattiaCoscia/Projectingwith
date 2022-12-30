package pawtropolis.controller;

import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.model.generationMethod.GenerationMethod;
import pawtropolis.utility.model.generationMethod.RandomChainDisposition;

public class GameMapGenerator {
	private static int dimY = 30;
	private static int dimX = 30;
	private final static GameMap gameMap = new GameMap(new Room[dimY][dimX]);
	
	public static GameMap run(Player player) {
		GenerationMethod generationMethod=new RandomChainDisposition(gameMap);
		return generationMethod.generateMap(player);
	}
}
