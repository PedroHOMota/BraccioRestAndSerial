package ie.tus.moveBraccio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEndPoint {
    InteractService service = new InteractService();

    @PostMapping(value = "/move")
    public ResponseEntity moveBraccio(@RequestBody Movement move){

        try {
            service.write(move);
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("");
        }
    }

    @GetMapping(value = "/tst")
    public ResponseEntity root(Movement move){


            return ResponseEntity.ok().body("root");

    }

}
