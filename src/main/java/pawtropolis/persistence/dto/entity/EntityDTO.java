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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="entity_type",
        discriminatorType = DiscriminatorType.STRING)
@Table(name = "entity")
public abstract class EntityDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "name")
    protected String name;
    @Column(name = "lifepoints")
    protected double lifePoints;
    @Column(name = "position_x")
    protected int positionX;
    @Column(name = "position_y")
    protected int positionY;
}
