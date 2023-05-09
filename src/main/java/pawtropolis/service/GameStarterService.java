package pawtropolis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawtropolis.controller.InputManager;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.view.RenderMapService;

import java.util.List;
import java.util.Scanner;

@Service
@Slf4j
public class GameStarterService {
    private Player player;
    private GameMapGeneratorService gameMapGeneratorService;
    private RenderMapService renderMapService;
    private InputManager inputManager;
    @Autowired
    public GameStarterService(Player player, GameMapGeneratorService gameMapGeneratorService, RenderMapService renderMapService, InputManager inputManager){
        this.player = player;
        this.gameMapGeneratorService = gameMapGeneratorService;
        this.renderMapService = renderMapService;
        this.inputManager = inputManager;
    }
    public void execute() {
        log.info("Choose your name");
        player.setName(inputManager.inputString());
        log.info("Write a seed on what the map will be generated");
        long seed = inputManager.inputString().hashCode();
        GameMap map = null;
        while(map == null){
            log.info("Choose generation type 'tree':(Tree like) 'cave':(Cavern like) 'frame':(Frame like)");
            String type = inputManager.chooseFromOptions("Choose generation type ", List.of("tree", "cave","frame"));
            map = gameMapGeneratorService.run(seed, type);
        }

        switch (inputManager.chooseFromOptions("type 'y'/'n' if you want to see all map ",List.of("y","n"))){
            case "y"-> renderMapService.setShowMap(true);
            default -> renderMapService.setShowMap(false);
        }
        renderMapService.printMap();
    }

}
