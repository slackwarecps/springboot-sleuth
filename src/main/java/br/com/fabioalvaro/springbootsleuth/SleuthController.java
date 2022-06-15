package br.com.fabioalvaro.springbootsleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SleuthController {


    @Autowired
    private SleuthService sleuthService;


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



}
