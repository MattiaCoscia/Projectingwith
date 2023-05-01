package pawtropolis.model.strategy;

public class UnknownStrategy implements ActionStrategy{
    @Override
    public ActionEnum execute(String action) {
        return ActionEnum.UNKNOWN_COMMAND;
    }
}
