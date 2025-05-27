package ch.wiss.m223.MovieWatchlist.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {

    @GetMapping(path = "/demo")
    public String getDemoPage(){
        return "Hello World ;) ";
    }

    @GetMapping(path = "private")
    public ResponseEntity<String> getTestPage() {
        return ResponseEntity.ok()
                .body("Protected page lalelu");
    }

    @GetMapping(path = "public")
    public ResponseEntity<String> getPublicPage() {
//        Thread.sleep(1000);
        return ResponseEntity.ok()
                .body("Public page lalelu");
    }
    @PostMapping(path = "/items")
    public ResponseEntity<String> getItems() {
        ResponseEntity<String> result = ResponseEntity.ok()
                .body("Admin Area");
        return result;
    }
}
