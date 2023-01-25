package pawtropolis.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.model.generationMethod.AdiacentNumberGeneration;
import pawtropolis.utility.model.generationMethod.RandomChainDisposition;

import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameMapGeneratorController {

	private static int dimY = 10;
	private static int dimX = 10;
	private final static GameMap gameMap = new GameMap(new Room[dimY][dimX]);
	
	public static GameMap run(Player player, List<Item> items, long seed) {
		switch(new Scanner(System.in).nextLine()) {
		case "1":{
			RandomChainDisposition generationMethod=new RandomChainDisposition(gameMap,seed);
			return generationMethod.generateMap(player, items);
		}
		case "2":{
			AdiacentNumberGeneration numberGenerator=new AdiacentNumberGeneration(gameMap,seed);
			return numberGenerator.generateMap(player, items);
		}
			default:{
				System.out.println("Unknown generation option!");
			}
		}
		return null;
	}
}
