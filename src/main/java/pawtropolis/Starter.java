package pawtropolis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pawtropolis.controller.ActionController;
import pawtropolis.controller.GameMapGeneratorController;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.utility.ItemContainer;
import pawtropolis.view.RenderMap;

import java.util.Scanner;

@SpringBootApplication
public class Starter implements ApplicationRunner {
	@Autowired
	private  Player player;
    public static void main(String[] args) {
		SpringApplication.run(Starter.class,args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Choose your name");
		player.setName(new Scanner(System.in).nextLine());
		GameMap map= null;
		Scanner sc=new Scanner(System.in);
		System.out.println("Write a seed on what the map will be generated");
		long seed=sc.nextLine().hashCode();
		while(map == null) {
			System.out.println("Choose generation type 1:(Tree like) 2:(Cavern like)");
			map=GameMapGeneratorController.run(player,ItemContainer.getItems(),seed);
		}
		System.out.println("choose if you want to see all map digit 'yes' and send else just send");
		if(sc.nextLine().toLowerCase().replace(" ", "").equals("yes")) {
			RenderMap.setShowMap(true);
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
