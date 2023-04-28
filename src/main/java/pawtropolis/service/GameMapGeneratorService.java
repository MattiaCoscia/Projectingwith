package pawtropolis.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.ItemStored;
import pawtropolis.model.map.GameMap;
import pawtropolis.utility.ItemContainer;
import pawtropolis.utility.model.generationmethod.CaveAdiacentNumberGeneration;
import pawtropolis.utility.model.generationmethod.FrameAdiacentNumberGeneration;
import pawtropolis.utility.model.generationmethod.RandomChainDisposition;

import java.util.List;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class GameMapGeneratorService {

	private Player player;
	private GameMap gameMap;
	private List<ItemStored> itemStoreds;
	@Autowired
	public GameMapGeneratorService(Player player, GameMap gameMap){
		itemStoreds = ItemContainer.getItems();
	}
	public GameMap run(long seed, String type) {
		switch (type) {
			case "tree" -> {
				RandomChainDisposition generationMethod = new RandomChainDisposition(gameMap, seed);
				return generationMethod.generateMap(player, itemStoreds);
			}
			case "cave" -> {
				CaveAdiacentNumberGeneration numberGenerator = new CaveAdiacentNumberGeneration(gameMap, seed);
				return numberGenerator.generateMap(player, itemStoreds);
			}
			case "frame" -> {
				FrameAdiacentNumberGeneration numberGenerator = new FrameAdiacentNumberGeneration(gameMap, seed);
				return numberGenerator.generateMap(player, itemStoreds);
			}
			default -> {
				return null;
			}
		}
	}
}
