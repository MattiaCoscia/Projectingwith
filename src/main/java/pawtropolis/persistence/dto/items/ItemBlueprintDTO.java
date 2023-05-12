package pawtropolis.persistence.dto.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.persistence.dto.DTOClass;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item_blueprint")
public class ItemBlueprintDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "volume")
    private int volume;
}
