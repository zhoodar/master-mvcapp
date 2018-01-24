package kg.jedi.master.service;

import kg.jedi.master.domain.Role;
import kg.jedi.master.domain.User;

/**
 * @author Zhoodar on 1/24/18.
 */
public interface UserService {

    User buildAndSave(String uname, String pass, Role role);
}
