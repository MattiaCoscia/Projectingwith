package pawtropolis.utility.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionFactory {
    private GoStrategy goStrategy;
    private GetStrategy getStrategy;
    private DropStrategy dropStrategy;
    private LookStrategy lookStrategy;
    private BagStrategy bagStrategy;
    private ExitStrategy exitStrategy;

    @Autowired
    public ActionFactory(GoStrategy goStrategy, GetStrategy getStrategy, DropStrategy dropStrategy, LookStrategy lookStrategy, BagStrategy bagStrategy,ExitStrategy exitStrategy) {
        this.goStrategy = goStrategy;
        this.getStrategy = getStrategy;
        this.dropStrategy = dropStrategy;
        this.lookStrategy = lookStrategy;
        this.bagStrategy = bagStrategy;
        this.exitStrategy= exitStrategy;
    }

    public ActionStrategy getAction(String action){
        switch (ActionEnum.fromValue(action)) {
            case GO:
                return goStrategy;

            case GET:
                return getStrategy;

            case DROP:
                return dropStrategy;

            case BAG:
                return bagStrategy;

            case LOOK:
                return lookStrategy;

            case EXIT:
                return exitStrategy;
            default:
                return new UnknownStrategy();
        }
    }
}
