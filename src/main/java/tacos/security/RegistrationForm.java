package tacos.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.MyUser;
import tacos.Role;

import java.util.Arrays;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    public MyUser toUser(PasswordEncoder passwordEncoder){
        return new MyUser(username,passwordEncoder.encode(password),fullname,street,city,state,zip,phoneNumber, Arrays.asList(new Role("ROLE_USER")));
    }
}
