package pawtropolis.persistence.dto.entity.npc.animal.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class AnimalDTOWithTail extends AnimalDTO {
    @Column(name = "tail_lenght")
    private double tailLenght;

}
