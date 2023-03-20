package pawtropolis.model.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import pawtropolis.model.items.Bag;
import pawtropolis.model.items.Item;

@Data
@Component
public class
Player extends Entity {
	private Bag bag;

	public Player() {
		this.bag = new Bag();
	}

	public void addItem(Item item) {
		Item itemToAdd = this.bag.getItems().get(item.getName());
		if (itemToAdd != null) {
			itemToAdd.setQuantity(itemToAdd.getQuantity() + item.getQuantity());
		} else {
			this.bag.getItems().put(item.getName(), item);
		}
	}

	public Item removeItem(String item) {
		Item itemToRemove = this.bag.getItems().get(item);
		if (itemToRemove != null) {
			if(itemToRemove.getQuantity() <= 1){
				this.bag.getItems().remove(itemToRemove.getName(),itemToRemove);
			}
			itemToRemove.decreaseQuantity();
			return new Item(itemToRemove.getName(), itemToRemove.getDescription(), itemToRemove.getVolume(), 1);
		} else {
			return null;
		}
	}
}
