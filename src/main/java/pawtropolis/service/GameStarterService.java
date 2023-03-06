package pawtropolis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.view.RenderMapService;

import java.util.Scanner;

@Service
@Slf4j
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
        log.info("Choose your name");
        player.setName(new Scanner(System.in).nextLine());
        Scanner sc=new Scanner(System.in);
        log.info("Write a seed on what the map will be generated");
        long seed=sc.nextLine().hashCode();
        while(!isMapGenerated) {
            log.info("Choose generation type 1:(Tree like) 2:(Cavern like)");
            GameMap generatedMap = gameMapGeneratorService.run(seed);
            isMapGenerated = generatedMap != null;
            if(isMapGenerated){
                map.setRooms(generatedMap.getRooms());
            }
        }
        log.info("choose if you want to see all map digit 'yes' and send else just send");
        if(sc.nextLine().toLowerCase().replace(" ", "").equals("yes")) {
            renderMapService.setShowMap(true);
        }
        renderMapService.printMap();
    }

}
