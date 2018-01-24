package kg.jedi.master.domain.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jedi on 1/24/18.
 */

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable {

    @JsonProperty("className")
    @Transient
    private String className;

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    public BaseEntity() {
        className = getClass().getSimpleName();
    }

    public BaseEntity(Long id) {
        this();
        this.id = id;
    }

    public static boolean equals(BaseEntity e1, BaseEntity e2) {
        if ((null != e1 && null == e2) || (null != e2 && null == e1)) {
            return false;
        }
        if (null == e1 && null == e2) {
            return true;
        }
        return e1.getId().equals(e2.getId());
    }

    public Object clone()throws CloneNotSupportedException{
        return (BaseEntity)super.clone();
    }
}