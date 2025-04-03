package ie.tus.moveBraccio;

import ie.tus.moveBraccio.dto.RobotMotorsDto;
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
    public ResponseEntity<RobotMotorsDto> moveBraccio(@RequestBody RobotMotors move){

        try {
            final RobotMotors initial = service.readPosition();
            service.write(move);
            Thread.sleep(1500);
            final RobotMotors end = service.readPosition();

            final RobotMotorsDto dto = new RobotMotorsDto(initial,end);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/move")
    public ResponseEntity<RobotMotors> readRobotPosition() throws Exception{
        RobotMotors robotMotors = service.readPosition();

        return ResponseEntity.ok().body(robotMotors);

    }

    @GetMapping(value = "/tst")
    public ResponseEntity<RobotMotorsDto> tst(){

        try {
            final RobotMotors initial = service.readPosition();


            final RobotMotors end = service.readPosition();

            final RobotMotorsDto dto = new RobotMotorsDto(initial,end);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
