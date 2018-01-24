package kg.jedi.master.controller;

import kg.jedi.master.common.Constants;
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
        return Constants.INDEX_PAGE;
    }

    @GetMapping(value = {"/masters/{id:[\\d]+}/appointment"})
    public String appointment(@PathVariable Long masterId, Model model) {
        model.addAttribute("master", masterRepository.findOne(masterId));

        return Constants.APPOINTMENT_PAGE;
    }
}
