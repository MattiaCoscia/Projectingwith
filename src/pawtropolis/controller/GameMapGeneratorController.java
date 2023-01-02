package pawtropolis.controller;

import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.model.generationMethod.AdiacentNumberGeneration;
import pawtropolis.utility.model.generationMethod.RandomChainDisposition;

import java.util.List;
import java.util.Scanner;

public class GameMapGeneratorController {
	private static int dimY = 10;
	private static int dimX = 10;
	private final static GameMap gameMap = new GameMap(new Room[dimY][dimX]);
	
	public static GameMap run(Player player, List<Item> items) {
		System.out.println("Choose generation type 1:(Tree like) 2:(Cavern like)");
		switch(new Scanner(System.in).nextLine()) {
		case "1":{
			RandomChainDisposition generationMethod=new RandomChainDisposition(gameMap);
			return generationMethod.generateMap(player, items);
		}
		case "2":{
			AdiacentNumberGeneration numberGenerator=new AdiacentNumberGeneration(gameMap);
			return numberGenerator.generateMap(player, items);
		}
		}
		return null;
	}
}
