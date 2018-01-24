package kg.jedi.master.service.impl;

import kg.jedi.master.domain.Master;
import kg.jedi.master.domain.Role;
import kg.jedi.master.domain.User;
import kg.jedi.master.repository.MasterRepository;
import kg.jedi.master.repository.RoleRepository;
import kg.jedi.master.service.MasterService;
import kg.jedi.master.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * @author Jedi on 1/24/18.
 */
@Service
public class DefaultMasterService implements MasterService {
    private final MasterRepository masterRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public DefaultMasterService(MasterRepository masterRepository, UserService userService, RoleRepository roleRepository) {
        this.masterRepository = masterRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Master save(Master master) {
        Role role = roleRepository.findOneByName("MASTER");
        User newUser = userService.buildAndSave(master.getEmail(), genTempPassword(), role);

        master.setUser(newUser);
        return masterRepository.save(master);
    }

    @Override
    public Master findById(Long id) {
        return masterRepository.findOne(id);
    }

    @Override
    public Master update(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public List<Master> findAll() {
        return masterRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        masterRepository.delete(id);
    }

    private String genTempPassword() {
        return "temp" + LocalTime.now() + "pass";
    }
}
