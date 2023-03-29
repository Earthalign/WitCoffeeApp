package WWapp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserCustomizerService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserCustomizer(user);
    }

    public void delete(Integer id){
        Long count = repo.countById(id);
        if(count==null || count==0)
        {
            System.out.println("Here is no user");
        }
        repo.deleteById(id);

    }

    public User get(int id) throws NotFoundException {

        Optional<User> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new NotFoundException("I can't find user with id: " + id);

    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public User get(Integer id) {
        return repo.findById(id).get();
    }

    public void userRole (User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role roleUser = roleRepository.findByName("User");
         user.addRole(roleUser);

        repo.save(user);
    }

    public List<User> listAllUser() {
        return (List<User>) repo.findAll();
    }
   /* public User listUser() {
        //return User;
        return 0;
    }
*/
    public void saveEditUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
    }


/*
    public User getUser(String id) {
        User user = users.stream()
                .filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElse(null);
        return user;
    }
    */

}

