package ie.tus.moveBraccio;

import ie.tus.moveBraccio.event.ArduinoReadyEvent;
import ie.tus.moveBraccio.singleton.EventBusSingleton;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoveRobotController {
    MoveRobotService service = new MoveRobotService();

    @PostMapping(value = "/move")
    public ResponseEntity moveBraccio(@RequestBody Movement move){

        try {
            service.write(move);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/tst")
    public ResponseEntity root(Movement move){
        service.sendEvent();
            return ResponseEntity.ok().body("root");

    }

    @GetMapping(value = "/t")
    public ResponseEntity root3(Movement move){
        EventBusSingleton.getEventBus().register(new ArduinoReadyEvent());

        return ResponseEntity.ok().body("2");

    }

}
