package kg.jedi.master.domain;

import kg.jedi.master.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Jedi on 1/24/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"master", "service"})

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
    private LocalDate scheduledDate;

    @Column(name = "scheduled_at")
    private String scheduledAt;

    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private Master master;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Transient
    private String scheduledDay;
}
