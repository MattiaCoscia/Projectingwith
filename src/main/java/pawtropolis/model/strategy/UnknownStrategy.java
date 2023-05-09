package pawtropolis.model.strategy;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UnknownStrategy extends Strategy{
    public UnknownStrategy() {
        super(ActionEnum.UNKNOWN);
    }
    @Override
    public ActionEnum execute(List<String> action) {
        return ActionEnum.UNKNOWN;
    }

}
