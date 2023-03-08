package pawtropolis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pawtropolis.controller.ActionController;
import pawtropolis.service.GameStarterService;
import pawtropolis.utility.strategy.ActionEnum;
import pawtropolis.view.RenderMapService;
import pawtropolis.view.model.MainFrame;

@Slf4j
@SpringBootApplication
public class Starter implements ApplicationRunner {

    @Autowired
    private ActionController actionController;
    @Autowired
    private GameStarterService gameStarterService;
    @Autowired
    private RenderMapService renderMapService;

    public static void main(String[] args) {
        //SpringApplication.run(Starter.class, args);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Starter.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        gameStarterService.execute();
        ActionEnum actionEnum = null;
        while (!ActionEnum.EXIT.equals(actionEnum)) {
            actionEnum = actionController.playerAction();
            if (ActionEnum.UNKNOWN_COMMAND.equals(actionEnum)) {
                log.info("command not existent");
            } else if(!ActionEnum.EXIT.equals(actionEnum)){
                renderMapService.printMap();
            }
        }
    }
}
