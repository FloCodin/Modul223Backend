package ch.wiss.m223.MovieWatchlist.democontroller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {


    @GetMapping(path = "/demo")
    public String getDemoPage(){
        return "Hello World ;) ";
    }
}
