package pawtropolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.utility.strategy.*;

import java.util.*;
@Component
public class ActionController {
    private GoStrategy goStrategy;
    private GetStrategy getStrategy;
    private DropStrategy dropStrategy;
    private LookStrategy lookStrategy;
    private BagStrategy bagStrategy;

    @Autowired
    public ActionController(GoStrategy goStrategy, GetStrategy getStrategy, DropStrategy dropStrategy, LookStrategy lookStrategy, BagStrategy bagStrategy) {
        this.goStrategy = goStrategy;
        this.getStrategy = getStrategy;
        this.dropStrategy = dropStrategy;
        this.lookStrategy = lookStrategy;
        this.bagStrategy = bagStrategy;
    }

    private final Scanner sc = new Scanner(System.in);

    public ActionEnum playerAction(GameMap map, Player player) {
        String[] action = sc.nextLine().split(" ");
       return ActionEnum.getAction(action[0]).execute(action[1]);
    }




}
