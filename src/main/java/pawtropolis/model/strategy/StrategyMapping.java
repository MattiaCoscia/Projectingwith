package pawtropolis.model.strategy;

import pawtropolis.model.map.DirectionEnum;
import pawtropolis.utility.ItemContainer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class StrategyMapping {
    public final static Map<ActionEnum,Integer> NUMBER_OF_PARAMETER_PER_STRATEGY = Map.of(
            ActionEnum.LOOK,0,
            ActionEnum.BAG,0,
            ActionEnum.GO,1,
            ActionEnum.GET,1,
            ActionEnum.DROP,1,
            ActionEnum.EXIT,0,
            ActionEnum.UNKNOWN,0
    );
    public final static Map<ActionEnum, List<String>> POSSIBLE_PARAMETERS_PER_STRATEGY = Map.of(
            ActionEnum.GET,ItemContainer.availableItemsNames(),
            ActionEnum.DROP,ItemContainer.availableItemsNames(),
            ActionEnum.GO, Stream.of(DirectionEnum.values()).map(directionEnum -> directionEnum.name().toLowerCase()).toList()
    );
}
