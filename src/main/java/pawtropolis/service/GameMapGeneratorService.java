package pawtropolis.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.model.generationMethod.AdiacentNumberGeneration;
import pawtropolis.utility.model.generationMethod.RandomChainDisposition;

import java.util.List;
import java.util.Scanner;
@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameMapGeneratorService {

	@Autowired
	private Player player;
	@Autowired
	private GameMap gameMap;

	@Autowired
	private List<Item> items;
	private int dimY = 10;
	private int dimX = 10;

	public  GameMap run(long seed) {
		gameMap.setRooms(new Room[dimY][dimX]);
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
