package pawtropolis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Bag;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.ItemContainer;

import java.util.List;

@Configuration
public class ConfigBeans {
    @Bean
    public GameMap gameMap() {
        return new GameMap(new Room[1][1]);
    }

    @Bean
    public List<Item> items() {
        return ItemContainer.getItems();
    }

}
