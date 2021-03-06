package kg.jedi.master.controller;

import kg.jedi.master.common.PathNames;
import kg.jedi.master.domain.Appointment;
import kg.jedi.master.domain.Master;
import kg.jedi.master.repository.AppointmentRepository;
import kg.jedi.master.service.MasterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

/**
 * @author Zhoodar on 1/25/18.
 */
@Controller
public class AppointmentController {
    private final MasterService masterService;
    private final AppointmentRepository appointmentRepository;

    public AppointmentController(MasterService masterService, AppointmentRepository appointmentRepository) {
        this.masterService = masterService;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/masters/{id:[\\d]+}/aptment/new")
    public String appointment(@PathVariable("id") Long masterId, Model model) {
        Master master = masterService.findById(masterId);
        Appointment appointment = new Appointment();
        appointment.setMaster(master);

        model.addAttribute("aptment", appointment);

        return PathNames.APPOINTMENT_PAGE;
    }

    @PostMapping("/aptment/book")
    public String bookAppointment(Appointment appointment, SessionStatus status) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        try {
            date = dateFormat.parse(appointment.getScheduledDay());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        appointment.setScheduledDate(localDate);

        appointmentRepository.save(appointment);
        status.setComplete();
        return "redirect:/masters/" + appointment.getMaster().getId();
    }

    @ResponseBody
    @GetMapping("/appointment/hours")
    public String getDayHours() {
        int min = 2;
        int max = 10;
        Random r = new Random();
        int random = r.nextInt((max - min) + 1) + min;
        LocalTime localTime = LocalTime.now().withHour(9).withMinute(0);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= random; i++) {
            LocalTime plus = localTime.plusHours((long) i);
            sb.append(" <option value='" +plus.getHour()+":00'>" +plus.getHour() + ":00 </option> ");
        }

        return sb.toString();
    }
}
