package WWapp;


import WWapp.Availability.Availability;
import WWapp.Availability.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller //Informacja dla Springa o kontrolerze
public class MainController {

    @Autowired
    private UserRepository repo;

    @Autowired
    UserCustomizerService uService;

    @Autowired
    private AvailabilityRepository aRepo;

    @Autowired
    private RoleRepository rRepo;

    @GetMapping("")                                         //adnotacja do przechwytywania naszych rządań
    public String indexPage(){
        return "index";
    }

    @GetMapping("/coffee")
    public String coffeePage() {
        return "coffee";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(RedirectAttributes r) {
        r.addFlashAttribute("message", "Podany login lub hasło są nieprawidłowe");
        return "login";
    }

    @GetMapping("/login?error")
    public String errorLogin(RedirectAttributes e) {
        e.addFlashAttribute("mess", "Podany login lub hasło są nieprawidłowe");
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }



    @GetMapping("/succesDelete")
    public String succesDelete() {
        return "succesDelete";
    }

    @GetMapping("/error")
    public String errorRole(){
        return "error";
    }

    @GetMapping("/calendar")     //adnotacja do przechwytywania naszych rządań
    public String calendarPage(){
        return "calendar";
    }

    @GetMapping("/register")
    public String registerPage(Model model ){
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/list_users")
    public String showAllUsers(Model model){

        List<User>listUsers = (List<User>) repo.findAll();
        List<Availability> availabilityUser = (List<Availability>) aRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("availabilityUser", availabilityUser);
        return "allUsers";
    }

    @PostMapping("/process")
        public String registration(String Email, User user)  {

                uService.userRole(user);
                return "succesRegister";

    }

    @PostMapping("/users/save")
    public String saveEditUser(User user){
        uService.saveEditUser(user);
        return "redirect:/list_users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes rd)
    {
            if (repo.existsById(id)) {
                repo.deleteById(id);
                rd.addFlashAttribute("messagee ", "Użytkownik został skasowany");
                System.out.println("Użytkownik " + id + " został skasowany");
            }
            else
            {
                System.out.println(" Nie mogę znaleźć takiego użytkownika ");

            }
            return "succesDelete";


    }

    @GetMapping("/users/edit/{id}")
        public String editUser(@PathVariable("id") Integer id, Model model){
        List<Role> listRoles = uService.getRoles();
        model.addAttribute("listRoles", listRoles);
        User user = uService.get(id);
        model.addAttribute("user", user);
        return "editUser";
    }



}