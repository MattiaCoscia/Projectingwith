package pawtropolis;

import pawtropolis.controller.ActionController;
import pawtropolis.controller.GameMapGeneratorController;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.view.RenderMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Avvio {
    public static void main(String[] args) {
		Item milk=new Item("milk","a bottle of milk",2);
		Item apple=new Item("apple","an apple",1);
		Item banana=new Item("banana","a banana",1);
		List<Item> items=new ArrayList<>();
		items.add(milk);
		items.add(apple);
		items.add(banana);
		System.out.println("Choose your name");
    	Player player=Player.getInstance(new Scanner(System.in).nextLine());
    	GameMap map= GameMapGeneratorController.run(player,items);
    	RenderMap.printMap(map,player);
		ActionController ac=new ActionController();
		//o

		while(true){
			String s=ac.playerAction(map,player);
			if(s.equals("go") || s.equals("get") || s.equals("drop")){
				RenderMap.printMap(map,player);
			}
		}
    }
}
