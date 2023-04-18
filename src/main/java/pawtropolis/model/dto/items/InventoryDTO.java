package pawtropolis.model.dto.items;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
public class InventoryDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(mappedBy = "inventory")
    private Map<String, ItemStoredDTO> items;
    public InventoryDTO(){
        items=new HashMap<>();
    }
}
