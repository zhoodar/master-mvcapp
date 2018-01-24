package kg.jedi.master.domain;

import kg.jedi.master.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Jedi on 1/24/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "services")
public class Service extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
