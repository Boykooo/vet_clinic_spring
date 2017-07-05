package security;

import entities.BaseUser;
import enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;
import repository.UserRepository;
import util.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        BaseUser user;
        if (UserUtils.defineUserType(email) == UserType.USER){
            user = userRepository.findOne(email);
        }
        else {
            user = employeeRepository.findOne(email);
        }

        if (user == null){
            throw new UsernameNotFoundException(email);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return buildUserForAuthorization(user, authorities);
    }

    private User buildUserForAuthorization(BaseUser user, List<GrantedAuthority> authorities){
        return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
    }

    public Optional<BaseUser> findById(String email) {
        BaseUser user;
        if (UserUtils.defineUserType(email) == UserType.USER){
            user = userRepository.findOne(email);
        }
        else {
            user = employeeRepository.findOne(email);
        }

        return Optional.ofNullable(user);
    }
}
