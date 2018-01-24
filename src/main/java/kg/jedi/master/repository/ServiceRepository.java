package kg.jedi.master.repository;

import kg.jedi.master.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zhoodar on 1/24/18.
 */
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
