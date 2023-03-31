package pawtropolis.model.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import pawtropolis.model.items.Bag;
import pawtropolis.model.items.Inventory;
import pawtropolis.model.items.ItemStored;

@Data
@Component
@jakarta.persistence.Entity
public class Player extends Entity {
	private Bag bag;

	public Player() {
		this.bag = new Bag(new Inventory());
	}

	public void addItem(ItemStored itemStored) {
		this.bag.addItem(itemStored);
	}

	public ItemStored getItem(String item) {
		return bag.getItem(item);
	}
}
