package pawtropolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Bag;
import pawtropolis.model.map.GameMap;
import pawtropolis.utility.strategy.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class ActionController {
    @Autowired
    private ActionFactory actionFactory;

    @Autowired
    private GameMap map;
    @Autowired
    private Player player;
    private final Scanner sc = new Scanner(System.in);

    public ActionEnum playerAction() {
        String[] action = sc.nextLine().split(" ");
        long start = System.currentTimeMillis();
        action = ObjectUtils.isEmpty(action) || ObjectUtils.isEmpty(action[0]) ? new String[]{" "}: action;
        /*ActionEnum actionsExecute = action.length < 2 || ObjectUtils.isEmpty(action[1])
               ? actionFactory.getAction(action[0]).execute("")
               : actionFactory.getAction(action[0]).execute(action[1]);*/

        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("pawtropolis.utility.strategy".replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Set<Class<ActionStrategy>> actionsStrategySet = reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> makeClassByName(line, "pawtropolis.utility.strategy"))
                .filter(Predicate.not(Objects::isNull))
                .filter(ActionStrategy.class::isAssignableFrom)
                .map(c -> (Class<ActionStrategy>) c)
                .collect(Collectors.toSet());

        String[] finalAction = action;
        List<ActionStrategy> choosenStrategy = actionsStrategySet.stream()
                .map(c -> makeInstanceFromClass(c, player, map))
                .filter(Predicate.not(Objects::isNull))
                .filter(c-> c.getClass().getSimpleName().toLowerCase().contains(finalAction[0].toLowerCase()))
                .collect(Collectors.toList());

        if(action.length < 2){
            action = new String[]{action[0],""};
        }
        ActionEnum actionsExecute = choosenStrategy.size() < 1 ? ActionEnum.UNKNOWN_COMMAND : choosenStrategy.get(0).execute(action[1]);
        long end = System.currentTimeMillis();
        System.out.println("tempo necessario "+(end-start)+"ms");
        return actionsExecute;
    }
    private static Class<?> makeClassByName(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (Exception e) {
            // log error
        }
        return null;
    }

    public static <T> T makeInstanceFromClass(Class<T> clazz, Player player, GameMap map) {
        try {
            if(clazz.equals(BagStrategy.class)){
                return clazz.getConstructor(Bag.class).newInstance(player.getBag());
            }
            return clazz.getConstructor(Player.class, GameMap.class).newInstance(player,map);
        } catch (Exception e) {
            return null;
        }
    }


}
