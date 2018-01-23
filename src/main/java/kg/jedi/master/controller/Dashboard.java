package kg.jedi.master.controller;

import kg.jedi.master.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jedi on 1/23/18.
 */
@Controller
public class Dashboard {

    @GetMapping(value = "/")
    public String index() {
        return Constants.INDEX_PAGE;
    }
}
