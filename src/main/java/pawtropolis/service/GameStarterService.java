package pawtropolis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawtropolis.model.entity.Player;
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
        log.info("Choose generation type 'tree':(Tree like) 'cave':(Cavern like) 'frame':(Frame like)");
        String type = sc.nextLine();
        gameMapGeneratorService.run(seed, type);
        renderMapService.printMap();
    }

}
