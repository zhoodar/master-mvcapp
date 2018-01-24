package kg.jedi.master.domain;

import kg.jedi.master.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Jedi on 1/24/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity {

    @Column(name = "client_fname")
    private String clientFName;

    @Column(name = "client_lname")
    private String clientLName;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name = "client_phone")
    private String clientPhone;

    @Column(name = "scheduled_day")
    private LocalDate scheduledDay;

    @Column(name = "scheduled_at")
    private LocalTime scheduledAt;

    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private Master master;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;
}
