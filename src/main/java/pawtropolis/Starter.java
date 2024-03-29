package pawtropolis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pawtropolis.controller.ActionController;
import pawtropolis.service.GameStarterService;
import pawtropolis.model.strategy.ActionEnum;
import pawtropolis.view.RenderMapService;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

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
        SpringApplication.run(Starter.class, args);
    }

    @Override
    public void run(ApplicationArguments args){
        gameStarterService.execute();
        List<ActionEnum> actionEnum;
        while (true) {
            actionEnum = actionController.playerAction();
            if (actionEnum.contains(ActionEnum.UNKNOWN)) {
                log.info("typed a command not recognized ");
            } else if(actionEnum.contains(ActionEnum.EXIT)){
                break;
            }else{
                renderMapService.printMap();
            }
        }
    }
}
