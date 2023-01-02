package pawtropolis.controller;

import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.model.generationMethod.AdiacentNumberGeneration;
import pawtropolis.utility.model.generationMethod.RandomChainDisposition;

import java.util.List;

public class GameMapGeneratorController {
	private static int dimY = 10;
	private static int dimX = 10;
	private final static GameMap gameMap = new GameMap(new Room[dimY][dimX]);
	
	public static GameMap run(Player player, List<Item> items) {
		RandomChainDisposition generationMethod=new RandomChainDisposition(gameMap);
		return generationMethod.generateMap(player, items);
	}
	public static GameMap run2(Player player, List<Item> items) {
		AdiacentNumberGeneration numberGenerator=new AdiacentNumberGeneration(gameMap);
		return numberGenerator.generateMap(player, items);
	}
}
