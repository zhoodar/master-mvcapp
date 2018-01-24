package kg.jedi.master.controller;

import kg.jedi.master.common.PathNames;
import kg.jedi.master.domain.Master;
import kg.jedi.master.repository.MasterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jedi on 1/23/18.
 */
@Controller
public class DashboardController {

    private final MasterRepository masterRepository;

    public DashboardController(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @GetMapping(value = {"/"})
    public String index(Model model) {
        model.addAttribute("masters", masterRepository.findAll());
        return PathNames.INDEX_PAGE;
    }

    @GetMapping(value = {"/masters/{id:[\\d]+}/appointment"})
    public String appointment(@PathVariable("id") Long masterId, Model model) {
        Master one = masterRepository.findOne(masterId);
        model.addAttribute("master", one);

        return PathNames.APPOINTMENT_PAGE;
    }
}
