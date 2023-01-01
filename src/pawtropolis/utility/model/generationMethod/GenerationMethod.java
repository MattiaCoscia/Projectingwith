package pawtropolis.utility.model.generationMethod;

import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;

public abstract class GenerationMethod {
	public abstract GameMap generateMap(Player player);
}
