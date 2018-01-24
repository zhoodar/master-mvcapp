package kg.jedi.master.domain;

import kg.jedi.master.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Jedi on 1/24/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {

    private static final int TEXT_MIN_SIZE = 1;
    private static final int TEXT_MAX_SIZE = 500;

    @Column(name = "value")
    private Integer value;

    @Column(name = "comment")
    @Size(min = TEXT_MIN_SIZE, max = TEXT_MAX_SIZE)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

}
