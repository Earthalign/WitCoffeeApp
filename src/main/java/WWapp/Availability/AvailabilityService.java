package WWapp.Availability;
import WWapp.NotFoundException;
import WWapp.User;
import WWapp.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository aRepository;

    @Autowired
    private UserRepository userRepository;


    public String availabilitygetEmail (User user) {
        return user.getEmail();
    }

    public WWapp.User userEmail (String email) {
        return userRepository.findByEmail(email);
    }

    public List<Availability> listAvailability() {

        return (List<Availability>) aRepository.findAll();
    }

    public void saveAvailability(Availability availability) {
        aRepository.save(availability);

    }

    public Availability get(int id) throws NotFoundException {

        Optional<Availability> result = aRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new NotFoundException("Nie mogę znaleźc dostępności o podanym id " + id);

    }

    public void deleteA(Integer id) throws NotFoundException {
        Optional<Availability> de = aRepository.findById(id);
        if(id==null) {
            throw new NotFoundException("Dostępnośc o id:   " + id + " nie istnieje w bazie danych " );
        }
        aRepository.deleteById(id);
    }


}
