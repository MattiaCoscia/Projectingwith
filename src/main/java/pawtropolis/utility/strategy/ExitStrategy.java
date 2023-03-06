package pawtropolis.utility.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExitStrategy implements ActionStrategy{

    @Override
    public ActionEnum execute(String action) {
        log.info("SPID MERDA!");
        return ActionEnum.EXIT;
    }
}
