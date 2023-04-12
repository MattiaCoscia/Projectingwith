package pawtropolis.utility;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pawtropolis.model.items.ItemBlueprint;
import pawtropolis.model.items.ItemStored;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemContainer {

	
	public static List<ItemStored> getItems(){
		List<ItemStored> itemStoreds =new ArrayList<>();
		
		ItemStored milk=new ItemStored(new ItemBlueprint("milk","a bottle of milk",2),1);
		ItemStored apple=new ItemStored(new ItemBlueprint("apple","an apple",1),1);
		ItemStored banana=new ItemStored(new ItemBlueprint("banana","a banana",1),1);
		
		itemStoreds.add(milk);
		itemStoreds.add(apple);
		itemStoreds.add(banana);
		
		return itemStoreds;
	}

}
