package WWapp;


import WWapp.Availability.Availability;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String email;                                                   //E-mail is unique which means we can't have same e-mail 2 times
    @Column(nullable = false, length = 64)
    private String password;
    @Column(nullable = false, length = 64)
    private String name;
    @Column(nullable = false, length = 64)
    private String surname;
    @Column(nullable = false, length = 9)
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    public User(){
    }
    public User(String name){
        this.name=name;
    }
    public User(Integer id, String name){
        this.id=id;
        this.name=name;
    }
    @OneToMany(mappedBy = "user",
        cascade=CascadeType.ALL
    )
    private List<Availability> availabilityList = new ArrayList<>();
    public int getId() {
        return id;
    }
    //Setter
    public void setId(int id) {
        this.id = id;
    }
    //Getter
    public String getEmail() {
        return email;
    }
    //Setter
    public void setEmail(String email) {
        this.email = email;
    }
    //Getter
    public String getName() {
        return name;
    }
    //Setter
    public void setName(String name) {
        this.name = name;
    }
    //Getter
    public String getSurname() {
        return surname;
    }
    //Setter
    public void setSurname(String surname) {
        this.surname = surname;
    }
    //Getter
    public String getPassword() {
        return password;
    }
    //Setter
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void addRole(Role role)
    {
        this.roles.add(role);
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public List<Availability> getAvailabilitySet() {
        return availabilityList;
    }

    @Override
    public String toString() {
        return  name + " " + surname  ;
    }

}
