package WWapp;
import org.springframework.lang.Nullable;
import javax.persistence.*;
@Entity
@Table (name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    public  Role(String user)
    {
    }
    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
