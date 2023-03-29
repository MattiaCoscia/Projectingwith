package pawtropolis.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String name;
    protected double lifePoints;
    protected int positionX;
    protected int positionY;
}
