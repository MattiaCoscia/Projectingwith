package pawtropolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.strategy.*;
import pawtropolis.service.CommandService;

import java.util.*;
@Component
public class ActionController {
    private CommandService commandService;
    @Autowired
    public ActionController(CommandService commandService){
        this.commandService = commandService;
    }
    public List<ActionEnum> playerAction() {
        return commandService.workCommands();
    }
}
