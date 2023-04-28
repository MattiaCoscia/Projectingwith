package pawtropolis.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.model.BusinessClass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Entity implements BusinessClass {
    protected long id;
    protected String name;
    protected double lifePoints;
    protected int positionX;
    protected int positionY;
}
