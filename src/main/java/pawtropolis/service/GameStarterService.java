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
    private GameMapGeneratorService gameMapGeneratorService;
    @Autowired
    private RenderMapService renderMapService;
    public void execute() {
        log.info("Choose your name");
        player.setName(new Scanner(System.in).nextLine());
        Scanner sc = new Scanner(System.in);
        log.info("Write a seed on what the map will be generated");
        long seed = sc.nextLine().hashCode();
        GameMap map = null;
        while(map == null){
            log.info("Choose generation type 'tree':(Tree like) 'cave':(Cavern like) 'frame':(Frame like)");
            String type = sc.nextLine();
            map = gameMapGeneratorService.run(seed, type);
        }
        log.info("type 'true' if you want to see all map");
        switch (sc.nextLine().trim()){
            case "true"-> renderMapService.setShowMap(true);
            default -> renderMapService.setShowMap(false);
        }
        renderMapService.printMap();
    }

}
