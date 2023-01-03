package pawtropolis;

import pawtropolis.controller.ActionController;
import pawtropolis.controller.GameMapGeneratorController;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.utility.ItemContainer;
import pawtropolis.view.RenderMap;

import java.util.Scanner;


public class Avvio {
    public static void main(String[] args) {
		System.out.println("Choose your name");
    	Player player=Player.getInstance(new Scanner(System.in).nextLine());
    	GameMap map= null;
    	while(map == null) {
    		map=GameMapGeneratorController.run(player,ItemContainer.getItems());
    	}
    	RenderMap.printMap(map,player);
		ActionController ac=new ActionController();
		while(true){
			String s=ac.playerAction(map,player);
			 if(s == null) {
					System.out.println("command not existent");
				}else if(s.equals("go") || s.equals("get") || s.equals("drop")){
				RenderMap.printMap(map,player);
			}
		}
    }
}
