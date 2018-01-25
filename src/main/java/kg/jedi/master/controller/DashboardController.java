package kg.jedi.master.controller;

import kg.jedi.master.common.PathNames;
import kg.jedi.master.domain.Master;
import kg.jedi.master.domain.Review;
import kg.jedi.master.repository.MasterRepository;
import kg.jedi.master.repository.ReviewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Jedi on 1/23/18.
 */
@Controller
public class DashboardController {

    private final MasterRepository masterRepository;
    private final ReviewRepository reviewRepository;

    public DashboardController(MasterRepository masterRepository, ReviewRepository reviewRepository) {
        this.masterRepository = masterRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("masters", masterRepository.findAll());
        return PathNames.INDEX_PAGE;
    }

    @GetMapping("/masters/{id:[\\d]+}/review/new")
    public String processReview(@PathVariable("id") Long id, Model model) {
        Master master = masterRepository.findOne(id);
        Review review = new Review();
        review.setMaster(master);
        model.addAttribute(review);
        return PathNames.REVIEW_PAGE;
    }

    @PostMapping("/masters/{id:[\\d]+}/review/new")
    public String processReview(@PathVariable("id") Long id, Review review) {
        review.setId(null);
        reviewRepository.save(review);

        return "redirect:/masters/"+id;
    }
}
