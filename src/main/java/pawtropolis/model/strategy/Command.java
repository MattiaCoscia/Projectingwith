package pawtropolis.model.strategy;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Command {
    private ActionEnum actionEnum;
    private List<String> parameters;

    public Command(ActionEnum actionEnum,List<String> parameters){
        this.actionEnum = actionEnum;
        this.parameters = parameters;
    }
}
