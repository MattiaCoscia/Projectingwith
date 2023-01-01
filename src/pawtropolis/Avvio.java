package pawtropolis;

import pawtropolis.controller.GameMapGenerator;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.view.RenderMap;

public class Avvio {
    public static void main(String[] args) {
    	Player player=Player.getInstance("giovanni");
    	GameMap map= GameMapGenerator.run2(player);
    	RenderMap.printMap(map,player);
		//o
    }
}
