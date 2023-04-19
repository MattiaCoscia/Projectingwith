package pawtropolis.model.dto.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item_blueprint")
public class ItemBlueprintDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private int volume;

    public ItemBlueprintDTO(String name, String description, int volume){
        this.name = name;
        this.description = description;
        this.volume = volume;
    }
}
