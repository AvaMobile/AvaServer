package server;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
//How do we handle these passwords
public class ItemSearch {
    @GetMapping("/search/item")
    public String searchItem(HttpServletRequest request, Model model, @RequestParam String description) {

    }
}
