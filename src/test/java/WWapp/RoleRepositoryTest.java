package WWapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

    @Autowired
    RoleRepository rRepo;

    @Test
    public void createRole(){
        Role user = new Role("User");
        Role admin = new Role("Admin");
        rRepo.saveAll(List.of(user, admin));
        List<Role> listRoles = rRepo.findAll();
        assertThat(listRoles.size()).isEqualTo(2);
    }
}
