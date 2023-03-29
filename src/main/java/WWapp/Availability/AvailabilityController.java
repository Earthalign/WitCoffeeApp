package WWapp.Availability;

import WWapp.NotFoundException;
import WWapp.User;
import WWapp.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class AvailabilityController {

   @Autowired
    private AvailabilityService aService;

   @Autowired
   private AvailabilityRepository aRepository;

    @Autowired
    private UserRepository repo;

    @GetMapping("/time")     //adnotacja do przechwytywania naszych rządań
    public String timePage(){
        return "time";
    }


    @GetMapping("/availabilities")        //Uzyskiwanie wszystkich dostępności
    public String showAvailability(Model model) {
        List<User> listUser = (List<User>) repo.findAll();
        model.addAttribute("listUser", listUser);
        Object name = listUser;
        model.addAttribute("name", name);
        List<Availability> availabilityList = aService.listAvailability();   //ListAll z ProductService
        model.addAttribute("availabilityList", availabilityList);
        return "allAvailabilities";
    }

    @GetMapping("/availabilities/new")
    public String newAvailability(Model model, User user) {                 //Deklaracja modelu
        List<User> listUser = (List<User>) repo.findAll();
        model.addAttribute("availability", new Availability());             //Dodawanie obiektu availability
        model.addAttribute("listUser", listUser);                           //Thymeleaf obsługa listy użytkowników
        model.addAttribute("pageTitle","Dodaj dostępność ");                //Napis
        model.addAttribute("user", user);
        return "time";
    }

    @PostMapping("/availabilities/save")
    public String addAvailability(Principal principal, Availability availability, RedirectAttributes r) {
        aService.saveAvailability(availability);    //zapisywanie dostępności
       r.addFlashAttribute("message", "Dostępność została dodana");
        return "redirect:/availabilities";
    }

    @PostMapping("/availabilities/save/{id}")
    public String addAvailabilityUser(Principal principal, Availability availability, RedirectAttributes r) {
        aService.saveAvailability(availability);    //zapisywanie dostępności
        r.addFlashAttribute("message", "Dostępność została dodana");
        return "redirect:/availabilities";
    }

    @GetMapping("/availabilities/delete/{id}")
    public String deleteAvailability(@PathVariable("id") int id, RedirectAttributes r) {
        try {
            aService.deleteA(id);
            r.addFlashAttribute("messagee", "Dostępność z id:" + id + " została skasowana");
            System.out.println("Skasowałeś dostępność o id: " + id );
        } catch (NotFoundException p) {
            p.printStackTrace();
            r.addFlashAttribute("messagee", p.getMessage());
        }
        return "redirect:/availabilities";
    }



    @PutMapping("/{availabilityId}/user/{userId}")
    Availability timeWork(
            @PathVariable int availabilityId,
            @PathVariable int userId
    ){
        Availability availability = aRepository.findById(availabilityId).get();
        User user = repo.findById(userId).get();
        availability.assignUser(user);
        return aRepository.save(availability);

    }


    @GetMapping("/availabilities/edit/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model, RedirectAttributes r) {
        try {
            Availability availability = aService.get(id);
            List<User> listUser = (List<User>) repo.findAll();
       //     aService.saveAvailability(availability);    //zapisywanie dostępności
            model.addAttribute("listUser", listUser);   //Thymeleaf obsługa listy użytkowników
            model.addAttribute("availability", availability);
            model.addAttribute("pageTitle", " Edytuję dostępność (o id: ) " + id);
            return "time";
        } catch (NotFoundException p) {
            p.printStackTrace();
            r.addFlashAttribute("message", p.getMessage());
            return "redirect:/availabilities";
        }
    }



}
