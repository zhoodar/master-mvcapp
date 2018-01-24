package kg.jedi.master.service.impl;

import kg.jedi.master.domain.Role;
import kg.jedi.master.domain.User;
import kg.jedi.master.repository.UserRepository;
import kg.jedi.master.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Zhoodar on 1/24/18.
 */
@Service
public class DefaultUserService implements UserService{

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User buildAndSave(String uname, String pass, Role role) {
        User u = new User();
        u.setRole(role);
        u.setPassword(pass);
        u.setUsername(uname);
        return userRepository.save(u);
    }
}
