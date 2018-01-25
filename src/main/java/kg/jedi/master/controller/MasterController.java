package kg.jedi.master.controller;

import kg.jedi.master.common.PathNames;
import kg.jedi.master.domain.Master;
import kg.jedi.master.domain.Service;
import kg.jedi.master.repository.ServiceRepository;
import kg.jedi.master.service.MasterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jedi on 1/24/18.
 */
@Controller
@RequestMapping(value = "/masters")
public class MasterController {

    private final MasterService masterService;
    private final ServiceRepository serviceRepository;

    public MasterController(MasterService masterService, ServiceRepository serviceRepository) {
        this.masterService = masterService;
        this.serviceRepository = serviceRepository;
    }

    @GetMapping(value = "/new")
    public String initCreationForm(Model model) {
        Master master = new Master();
        List<Service> services = serviceRepository.findAll();

        model.addAttribute("master", master);
        model.addAttribute("services", services);
        return PathNames.CoU_PAGE;
    }

    @PostMapping(value = "/new")
    public String processCreationForm(@Valid Master master, SessionStatus status) {
        this.masterService.save(master);
        status.setComplete();
        return "redirect:/";
    }

    @GetMapping(value = "/{id:[\\d]+}/edit")
    public String initUpdateMasterForm(@PathVariable("id") Long id, Model model) {
        Master master = this.masterService.findById(id);
        List<Service> services = serviceRepository.findAll();

        for (Service s : master.getServices()) {
            services = services.stream().filter(service -> !service.equals(s)).collect(Collectors.toList());
        }

        model.addAttribute("master", master);
        model.addAttribute("services", services);
        return PathNames.CoU_PAGE;
    }

    @PostMapping(value = "/{id:[\\d]+}/edit")
    public String processUpdateMasterForm(@Valid Master master, SessionStatus status) {
        this.masterService.update(master);
        status.setComplete();
        return "redirect:/";
    }

    @GetMapping("/{id:[\\d]+}")
    public ModelAndView showMaster(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("masterDetails");
        Master master = this.masterService.findById(id);
        mav.addObject(master);
        return mav;
    }

    @GetMapping("/{id:[\\d]+}/delete")
    public String delete(@PathVariable("id") Long id, SessionStatus status) {
        masterService.delete(id);
        status.setComplete();
        return "redirect:/";
    }
}
