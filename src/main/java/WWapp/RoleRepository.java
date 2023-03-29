package WWapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long>
{

    @Query("SELECT r FROM  Role r where r.name = ?1")
    public Role findByName(String name);

}
