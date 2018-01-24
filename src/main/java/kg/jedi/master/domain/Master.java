package kg.jedi.master.domain;

import kg.jedi.master.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author Jedi on 1/24/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "masters")
public class Master extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "master_of")
    private String masterOf;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToMany
    @JoinTable(name = "master_services",
            joinColumns = {@JoinColumn(name = "master_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")})
    private List<Service> services;

    @OneToMany(mappedBy = "master", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "master", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Review> reviews;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Integer countReviews() {
        Integer count = 0;
        if(null != this.reviews && !this.reviews.isEmpty()) {
            Integer total = reviews.stream().mapToInt(Review::getValue).sum();
            count = total / reviews.size();
        }

        return count;
    }

    public boolean isPersist() {
        boolean result = false;
        if (null != id) {
            return true;
        }
        return result;
    }
}
