package pawtropolis.model.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ExitStrategy extends Strategy{
    @Autowired
    public ExitStrategy(){
        super(ActionEnum.EXIT);
    }

    @Override
    public ActionEnum execute(List<String> action) {
        log.info("SPID MERDA!");
        return ActionEnum.EXIT;
    }
}
