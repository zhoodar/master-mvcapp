package kg.jedi.master.service.impl;

import kg.jedi.master.domain.Master;
import kg.jedi.master.domain.Role;
import kg.jedi.master.domain.Service;
import kg.jedi.master.domain.User;
import kg.jedi.master.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author Zhoodar on 1/24/18.
 */
@Component
public class GenerateTestDataService implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MasterRepository masterRepository;
    private final ServiceRepository serviceRepository;

    public GenerateTestDataService(UserRepository userRepository, RoleRepository roleRepository, MasterRepository masterRepository, ServiceRepository serviceRepository, AppointmentRepository appointmentRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.masterRepository = masterRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        buildUser(buildRole("ADMIN"), "admin","admin");
        buildService("fix all type computers", "description");
        buildService("fix all type smart phones", "description");
        buildService("fix all type vehicle problems", "description");
        User master = buildUser(buildRole("MASTER"), "master","master");

        buildMaster("Master Baker", master);
    }


    private Role buildRole(String name) {
        Role r = new Role();
        r.setName(name);
        return roleRepository.save(r);
    }

    private User buildUser(Role role, String uname, String password) {
        User u = new User();
        u.setUsername(uname);
        u.setPassword(password);
        u.setRole(role);
        return userRepository.save(u);
    }

    private Service buildService(String name, String desc) {
        Service s = new Service();
        s.setName(name);
        s.setDescription(desc);
        return serviceRepository.save(s);
    }

    private Master buildMaster(String masterOf, User user) {
        Master m =  new Master();
        m.setMasterOf(masterOf);
        m.setFullName("Master Pi");
        m.setAddress("Kiev 94");
        m.setCity("Gotham");
        m.setServices(Collections.singletonList(buildService("cook, bake any kind of pies, cakes ", "desc goes here")));
        m.setEmail("email@mail.kg");
        m.setPhone("07XXX XX-XX-XX");
        m.setUser(user);
        return masterRepository.save(m);
    }
}
