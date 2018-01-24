package kg.jedi.master.service;

import kg.jedi.master.domain.Master;

import java.util.List;

/**
 * @author Zhoodar on 1/24/18.
 */
public interface MasterService {
    Master save(Master master);

    Master findById(Long id);

    Master update(Master master);

    List<Master> findAll();

    void delete(Long id);
}
