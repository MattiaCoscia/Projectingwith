package pawtropolis.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Entity {
    protected String name;
    protected double lifePoints;
    protected int positionX;
    protected int positionY;


}
