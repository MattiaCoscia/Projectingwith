package pawtropolis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pawtropolis.controller.ActionController;
import pawtropolis.service.GameStarterService;
import pawtropolis.utility.strategy.ActionEnum;
import pawtropolis.view.RenderMapService;

@SpringBootApplication
public class Starter implements ApplicationRunner {

    @Autowired
    private ActionController actionController;
    @Autowired
    private GameStarterService gameStarterService;
    @Autowired
    private RenderMapService renderMapService;

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        gameStarterService.execute();
        ActionEnum actionEnum = null;
        while (!ActionEnum.EXIT.equals(actionEnum)) {
            actionEnum = actionController.playerAction();
            if (ActionEnum.UNKNOWN_COMMAND.equals(actionEnum)) {
                System.out.println("command not existent");
            } else {
                renderMapService.printMap();
            }
        }
    }
}
