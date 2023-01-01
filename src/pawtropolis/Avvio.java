package pawtropolis;

import pawtropolis.controller.GameMapGeneratorController;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.view.RenderMap;

public class Avvio {
    public static void main(String[] args) {
    	Player player=Player.getInstance("giovanni");
    	GameMap map= GameMapGeneratorController.run(player);
    	RenderMap.printMap(map,player);
		//o
    }
}
