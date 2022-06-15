package br.com.fabioalvaro.springbootsleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController
public class SleuthController {


    @Autowired
    private SleuthService sleuthService;

    @Autowired
    private Executor executor;


    Logger logger = LoggerFactory.getLogger(SleuthController.class);

    @GetMapping("/piu")
   public String helloSleuth(){
        logger.info("Hello Sleuth, piu!");
       return "success";

    }

    @GetMapping("/same-span")
    public String helloSleuthSameSpan() throws InterruptedException {
        logger.info("Same Span");
        sleuthService.doSomeWorkSameSpan();
        return "success";
    }

    @GetMapping("/new-span")
    public String helloSleuthNewSpan() throws InterruptedException {
        logger.info("A1 :: New Span");
        sleuthService.doSomeWorkNewSpan();
        logger.info("A5 :: New Span :: success!!");
        return "success";
    }

    @GetMapping("/new-thread")
    public String helloSleuthNewThread() {
        logger.info("New Thread");
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("I'm inside the new thread - with a new span");
        };
        executor.execute(runnable);

        logger.info("I'm done - with the original span");
        return "success";
    }






}
