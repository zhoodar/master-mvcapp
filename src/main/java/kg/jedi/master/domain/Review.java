package kg.jedi.master.domain;

import kg.jedi.master.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Jedi on 1/24/18.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"master"})

@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {

    private static final int TEXT_MAX_SIZE = 500;

    @Column(name = "value")
    private Integer value;

    @Column(name = "comment", length = TEXT_MAX_SIZE)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

}
