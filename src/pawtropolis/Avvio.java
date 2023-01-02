package pawtropolis;

import pawtropolis.controller.ActionController;
import pawtropolis.controller.GameMapGeneratorController;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.view.RenderMap;

public class Avvio {
    public static void main(String[] args) {
    	Player player=Player.getInstance("giovanni");
    	GameMap map= GameMapGeneratorController.run2(player);
    	RenderMap.printMap(map,player);
		ActionController ac=new ActionController();
		//o

		while(true){
			ac.playerAction(map,player);
			RenderMap.printMap(map,player);
		}
    }
}
