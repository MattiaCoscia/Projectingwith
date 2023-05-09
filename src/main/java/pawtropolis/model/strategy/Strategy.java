package pawtropolis.model.strategy;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class Strategy {
    private ActionEnum action;
    protected Strategy(ActionEnum actionEnum){
        this.action = actionEnum;
    }
    public abstract ActionEnum execute(List<String> parameters);
}
