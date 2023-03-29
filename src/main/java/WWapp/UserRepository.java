package WWapp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//Operations on database table name User

@Repository
public interface UserRepository extends CrudRepository <User, Integer> {
    @Query("select e from User e where e.email = ?1")
    User findByEmail(String email);
    public long countById(Integer id);
}


