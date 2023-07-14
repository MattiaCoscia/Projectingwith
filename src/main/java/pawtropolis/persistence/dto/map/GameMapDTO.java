package pawtropolis.persistence.dto.map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.persistence.dto.DTOClass;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "game_map")
public class GameMapDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "width_map")
    private int widthMap;
    @Column(name = "height_map")
    private int heightMap;

    @OneToMany(cascade = CascadeType.ALL)
    @MapKeyEnumerated(EnumType.STRING)
    private Map<String, RoomDTO> rooms;
    public GameMapDTO() {
        this.rooms = new HashMap<>();
        widthMap = 10;
        heightMap = 10;
    }
}
