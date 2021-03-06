package internship.task.tasker.controller;

import internship.task.tasker.domain.plain.models.WebHookRepresentationJSON;
import internship.task.tasker.manager.DecisionMakingAnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebHookVerificationController {

    @Autowired
    private DecisionMakingAnswerService decision;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private static final String VERIFY_TOKEN = "token";

    @GetMapping("/webhook")
    public String verify(@RequestParam("hub.mode") String mode,
                         @RequestParam("hub.challenge") String challenge,
                         @RequestParam("hub.verify_token") String verify) {
        logger.info("Begin");

        logger.info("Received message from facebook ");
        logger.info(challenge);
        if ("subscribe".equals(mode)) {
            if (verify.equals(VERIFY_TOKEN)) {
                return challenge;
            }

        } else return "ERROR";

        return challenge;
    }
    // what we  get from user
    @PostMapping("/webhook")
    public void receiveMessages(@RequestBody WebHookRepresentationJSON webHook) {


        decision.makeDecision(webHook);

    }


}
