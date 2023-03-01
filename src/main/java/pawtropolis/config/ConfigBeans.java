package pawtropolis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Bag;

@Configuration
public class ConfigBeans {

    @Bean
    public Bag bag(){
        return new Bag();
    }

    @Bean
    public Player player(){
        return new Player(bag());
    }
}
