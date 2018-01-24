package kg.jedi.master.controller;

import kg.jedi.master.common.PathNames;
import kg.jedi.master.domain.Master;
import kg.jedi.master.domain.Service;
import kg.jedi.master.repository.ServiceRepository;
import kg.jedi.master.service.MasterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Jedi on 1/24/18.
 */
@Controller
public class MasterController {

    private final MasterService masterService;
    private final ServiceRepository serviceRepository;

    public MasterController(MasterService masterService, ServiceRepository serviceRepository) {
        this.masterService = masterService;
        this.serviceRepository = serviceRepository;
    }

    @GetMapping(value = "/masters/new")
    public String initCreationForm(Model model) {
        Master master = new Master();
        List<Service> services = serviceRepository.findAll();

        model.addAttribute("master", master);
        model.addAttribute("services", services);
        return PathNames.CoU_PAGE;
    }

    @PostMapping(value = "/masters/new")
    public String processCreationForm(@Valid Master master, SessionStatus status) {
        this.masterService.save(master);
        status.setComplete();
        return "redirect:/";
    }

    @GetMapping(value = "/masters/{id:[\\d]+}/edit")
    public String initUpdateMasterForm(@PathVariable("id") Long id, Model model) {
        Master master = this.masterService.findById(id);
        model.addAttribute(master);
        return PathNames.CoU_PAGE;
    }

    @PutMapping(value = "/masters/{id:[\\d]+}/edit")
    public String processUpdateMasterForm(@Valid Master master, SessionStatus status) {
        this.masterService.update(master);
        status.setComplete();
        return "redirect:/";
    }

    @GetMapping("/masters/{id:[\\d]+}")
    public ModelAndView showMaster(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("masterDetails");
        Master master = this.masterService.findById(id);
        mav.addObject(master);
        return mav;
    }

    @DeleteMapping("maters/{id:[\\d]+}")
    public String delete(@PathVariable("id") Long id, Model model) {
        masterService.delete(id);
        List<Master> masters = masterService.findAll();
        model.addAttribute("masters", masters);
        return PathNames.INDEX_PAGE;
    }
}
