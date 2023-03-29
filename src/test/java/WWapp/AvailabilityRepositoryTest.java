package WWapp;

import WWapp.Availability.Availability;
import WWapp.Availability.AvailabilityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AvailabilityRepositoryTest {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Test
    public void testAvailabilityCreate() {
        Availability availability = new Availability();
        availability.setStart("12.20");
       availability.setEnd("20:20");
        availability.setMonday(false);
        availability.setTuesday(false);
        availability.setWednesday(true);
        availability.setThursday(true);
        availability.setFriday(true);
        availability.setSaturday(false);
        availability.setSunday(false);
        //availability.setUser(availability.getUser());
        Availability savedAvailability = availabilityRepository.save(availability);
        Assertions.assertThat(savedAvailability).isNotNull();
        Assertions.assertThat(savedAvailability.getId()).isGreaterThan(0);
    }

    @Test
    public void testAvailabilityList(){

       Iterable <Availability> availabilities = availabilityRepository.findAll();
       Assertions.assertThat(availabilities).hasSizeGreaterThan(0);
        for (Availability availability : availabilities) {
            System.out.println(availability);
        }

    }


    @Test
    public void testAvailabilityEdit (){

        Integer id = 1;
        Optional<Availability> optionalAvailability = availabilityRepository.findById(id);
        Availability availability = optionalAvailability.get();
        availability.setStart("10.10");
        availabilityRepository.save(availability);

        Availability updatedAvailability = availabilityRepository.findById(id).get();
        Assertions.assertThat(updatedAvailability.getStart()).isEqualTo("10.10");

    }
/*
    @Test
    public void testGet(){

        Integer productId = 1;
        Optional<Product> optionalProduct = prepo.findById(productId);
        Assertions.assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct.get());
    }

    @Test
    public void testDelete(){
        Integer productId = 2;
        prepo.deleteById(productId);
        Optional<Product> optionalProduct = prepo.findById(productId);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }

    */


}

