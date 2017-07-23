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
import dao.EmployeeDao;
import dao.ClientDao;
import util.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        BaseUser user;
        if (UserUtils.defineUserType(email) == UserType.CLIENT){
            user = clientDao.findOne(email);
        }
        else {
            user = employeeDao.findOne(email);
        }

        if (user == null){
            throw new UsernameNotFoundException(email);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return buildUserForAuthorization(user, authorities);
    }

    private User buildUserForAuthorization(BaseUser user, List<GrantedAuthority> authorities){
        return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
    }

    public Optional<BaseUser> findById(String email) {
        BaseUser user;
        if (UserUtils.defineUserType(email) == UserType.CLIENT){
            user = clientDao.findOne(email);
        }
        else {
            user = employeeDao.findOne(email);
        }

        return Optional.ofNullable(user);
    }
}
