package pawtropolis.utility.strategy;

public class UnknownStrategy implements ActionStrategy{
    @Override
    public ActionEnum execute(String action) {
        System.out.println("UNKNOWN COMMAND");
        return ActionEnum.UNKNOWN_COMMAND;
    }
}
