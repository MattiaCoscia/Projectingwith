package pawtropolis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.view.RenderMapService;

import java.util.Scanner;

@Service
public class GameStarterService {
    @Autowired
    private Player player;
    @Autowired
    private GameMap map;
    @Autowired
    private GameMapGeneratorService gameMapGeneratorService;
    @Autowired
    private  RenderMapService renderMapService;
    private boolean isMapGenerated = false;

    public void execute(){
        System.out.println("Choose your name");
        player.setName(new Scanner(System.in).nextLine());
        Scanner sc=new Scanner(System.in);
        System.out.println("Write a seed on what the map will be generated");
        long seed=sc.nextLine().hashCode();
        while(!isMapGenerated) {
            System.out.println("Choose generation type 1:(Tree like) 2:(Cavern like)");
            GameMap generatedMap = gameMapGeneratorService.run(seed);
            isMapGenerated = generatedMap != null ? true : false;
            if(isMapGenerated){
                map.setRooms(generatedMap.getRooms());
            }
        }
        System.out.println("choose if you want to see all map digit 'yes' and send else just send");
        if(sc.nextLine().toLowerCase().replace(" ", "").equals("yes")) {
            renderMapService.setShowMap(true);
        }
        renderMapService.printMap();
    }

}
