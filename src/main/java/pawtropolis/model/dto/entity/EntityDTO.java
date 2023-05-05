package pawtropolis.model.dto.entity;

import jakarta.persistence.*;
import lombok.*;
import pawtropolis.model.dto.DTOClass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "entity")
public abstract class EntityDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String name;
    protected double lifePoints;
    protected int positionX;
    protected int positionY;
}
