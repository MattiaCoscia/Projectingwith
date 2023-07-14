package pawtropolis.persistence.dto.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.persistence.dto.DTOClass;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "item_stored")
public class ItemStoredDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "personal_name")
    private String personalName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blueprint_id", referencedColumnName = "id")
    private ItemBlueprintDTO itemBlueprintDTO;

    @Column(name = "hash_key")
    private int hashKey;
}
