package pawtropolis.utility.strategy;

import org.springframework.stereotype.Component;

@Component
public class ExitStrategy implements ActionStrategy{

    @Override
    public ActionEnum execute(String action) {
        System.out.println("SPID MERDA!");
        return ActionEnum.EXIT;
    }
}
