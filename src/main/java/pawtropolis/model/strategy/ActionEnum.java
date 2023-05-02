package pawtropolis.model.strategy;

public enum ActionEnum {
    GO("go"),
    BAG("bag"),
    GET("get"),
    DROP("drop"),
    LOOK("look"),
    EXIT("exit"),
    UNKNOWN("unknwon");
    private String action;

    ActionEnum(String action){
        this.action = action;
    }

    public static ActionEnum fromValue(String action) {
        try {
            return ActionEnum.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
