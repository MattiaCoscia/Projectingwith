package pawtropolis.model.items;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.model.BusinessClass;

@NoArgsConstructor
@Getter
@Setter
public class ItemBlueprint extends BusinessClass {
    private int id;
    private String name;
    private String description;
    private int volume;
    public ItemBlueprint(String name,String description,int volume){
        this.name = name;
        this.description = description;
        this.volume = volume;
    }
}
