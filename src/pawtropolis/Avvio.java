package pawtropolis;

import pawtropolis.controller.ActionController;
import pawtropolis.controller.GameMapGeneratorController;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.view.RenderMap;

import java.util.ArrayList;
import java.util.List;

public class Avvio {
    public static void main(String[] args) {
		Item milk=new Item("milk","a bottle of milk",2);
		Item apple=new Item("apple","an apple",1);
		Item banana=new Item("banana","a banana",1);
		List<Item> items=new ArrayList<>();
		items.add(milk);
		items.add(apple);
		items.add(banana);
    	Player player=Player.getInstance("giovanni");
    	GameMap map= GameMapGeneratorController.run2(player,items);
    	RenderMap.printMap(map,player);
		ActionController ac=new ActionController();
		//o

		while(true){
			ac.playerAction(map,player);
			RenderMap.printMap(map,player);
		}
    }
}
