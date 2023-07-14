package pawtropolis.persistence.dto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.persistence.dto.DTOClass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "entity")
public abstract class EntityDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected long id;
    protected String name;
    protected double lifePoints;
    protected int positionX;
    protected int positionY;
}
