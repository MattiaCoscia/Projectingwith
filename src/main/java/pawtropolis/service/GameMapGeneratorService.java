package pawtropolis.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
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

	@Autowired
	private Player player;
	@Autowired
	private GameMap gameMap;
	private List<Item> items = ItemContainer.getItems();

	public GameMap run(long seed, String type) {
		switch (type) {
			case "tree" -> {
				RandomChainDisposition generationMethod = new RandomChainDisposition(gameMap, seed);
				return generationMethod.generateMap(player, items);
			}
			case "cave" -> {
				CaveAdiacentNumberGeneration numberGenerator = new CaveAdiacentNumberGeneration(gameMap, seed);
				return numberGenerator.generateMap(player, items);
			}
			case "frame" -> {
				FrameAdiacentNumberGeneration numberGenerator = new FrameAdiacentNumberGeneration(gameMap, seed);
				return numberGenerator.generateMap(player, items);
			}
			default -> {
				return null;
			}
		}
	}
}
