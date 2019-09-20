package work.icql.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 15:42
 * @Title PdfController
 * @Description PdfController
 */
@Controller
@RequestMapping("pdf")
public class PdfController {
    @GetMapping
    public String index() {
        return "redirect:/pdf/index.html";
    }
}
