package pawtropolis.model.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pawtropolis.model.items.Bag;

@Data
@Component
public class
Player extends Entity {
	private Bag bag;
	public Player(){
		this.bag = new Bag();
	}
}
