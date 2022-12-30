package pawtropolis;

import pawtropolis.controller.GameMapGenerator;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.model.generationMethod.AdiacentNumberGeneration;
import pawtropolis.view.RenderMap;

public class Avvio {
    public static void main(String[] args) {
    	Player player=Player.getInstance("giovanni");
    	//GameMap map= GameMapGenerator.run(player);
        //RenderMap.printMap(map, player);
    	AdiacentNumberGeneration numberGenerator=new AdiacentNumberGeneration();
    	RenderMap.printMap(numberGenerator.AdiacentNumberGeneration(new GameMap(new Room[10][10]),player),player);
		//o
    }
}
