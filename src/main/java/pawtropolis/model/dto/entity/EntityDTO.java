package pawtropolis.model.dto.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "entity")
public abstract class EntityDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String name;
    protected double lifePoints;
    protected int positionX;
    protected int positionY;
}
