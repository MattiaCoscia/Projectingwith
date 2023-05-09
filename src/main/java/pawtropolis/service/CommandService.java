package pawtropolis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import pawtropolis.controller.InputManager;
import pawtropolis.model.strategy.ActionEnum;
import pawtropolis.model.strategy.Command;
import pawtropolis.model.strategy.Strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class CommandService {
    private Map<ActionEnum, Strategy> strategies;
    private InputManager inputManager;
    @Autowired
    public CommandService(ApplicationContext applicationContext, InputManager inputManager){
        this.inputManager = inputManager;
        this.strategies = new HashMap<>();
        applicationContext.getBeansOfType(Strategy.class).forEach((string,strategy) ->{
            strategies.put(strategy.getAction(),strategy);
        });
    }

    public ActionEnum workCommands(){
        List<Command> extrapoletedCommands = inputManager.inputPhraseAsCommands();
        int fg =3;
        for(Command command:extrapoletedCommands){
            ActionEnum actionEnum = strategies.get(command.getActionEnum()).execute(command.getParameters());
            if(ActionEnum.UNKNOWN.equals(actionEnum)){
                return ActionEnum.UNKNOWN;
            }
        }
        return ActionEnum.GO;
    }
}
