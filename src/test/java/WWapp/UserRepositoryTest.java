package WWapp;

import WWapp.Product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager testEntityManager;



    @Test
    public void testCreate(){
        User user = new User();
        user.setEmail("witoldwluczkowskii8@gmail.com");
        user.setPassword("123456789");
        user.setName("Witoldu");
        user.setSurname("Wluczko");
        user.setPhone("712327444");


        User saveUser = repo.save(user);
        User existUser = testEntityManager.find(User.class, saveUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserEmail(){
        String email = "wit@o2.pl";
        User user = repo.findByEmail(email);
        assertThat(user).isNotNull();
    }

    @Test
    public void testDelete(){
        Integer userId = 2;
        repo.deleteById(userId);

    }
}
