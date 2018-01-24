package kg.jedi.master.repository;

import kg.jedi.master.domain.Master;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zhoodar on 1/24/18.
 */
public interface MasterRepository extends JpaRepository<Master, Long> {
}
