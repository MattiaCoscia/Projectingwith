package pawtropolis.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pawtropolis.model.items.Bag;
import pawtropolis.model.items.Inventory;
import pawtropolis.model.items.ItemStored;

@Getter
@Setter
@Component
public class Player extends Entity {
	private Bag bag;

	public Player() {
		this.bag = new Bag(new Inventory());
	}

	public void addItem(ItemStored itemStored) {this.bag.addItem(itemStored);}

	public ItemStored getItem(String item) {
		return bag.getItem(item);
	}

	public void removeItem(ItemStored item){bag.removeItem(item);};
}
