package pawtropolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.utility.strategy.*;

import java.util.*;
@Component
public class ActionController {
    @Autowired
    private ActionFactory actionFactory;
    private final Scanner sc = new Scanner(System.in);

    public ActionEnum playerAction() {
        String[] action = sc.nextLine().split(" ");
        action = ObjectUtils.isEmpty(action) || ObjectUtils.isEmpty(action[0]) ? new String[]{" "}: action;
       return action.length < 2 || ObjectUtils.isEmpty(action[1])
               ? actionFactory.getAction(action[0]).execute("")
               : actionFactory.getAction(action[0]).execute(action[1]);
    }




}
