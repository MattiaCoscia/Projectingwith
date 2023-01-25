package pawtropolis.utility;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pawtropolis.model.items.Item;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemContainer {

	
	public static List<Item> getItems(){
		List<Item> items=new ArrayList<>();
		
		Item milk=new Item("milk","a bottle of milk",2);
		Item apple=new Item("apple","an apple",1);
		Item banana=new Item("banana","a banana",1);
		
		items.add(milk);
		items.add(apple);
		items.add(banana);
		
		return items;
	}

}
