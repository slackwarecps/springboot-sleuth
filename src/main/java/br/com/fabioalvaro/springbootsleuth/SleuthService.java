package br.com.fabioalvaro.springbootsleuth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class SleuthService {
    Logger logger = LoggerFactory.getLogger(SleuthService.class);

    @Autowired
    private Tracer tracer;



    public void doSomeWorkSameSpan() throws InterruptedException {
        Thread.sleep(1000L);
        logger.info("Doing some work");

    }


    public void doSomeWorkNewSpan() throws InterruptedException {
        logger.info("A2:: I'm in the original span");

        Span newSpan = tracer.nextSpan().name("newSpan").start();
        try (Tracer.SpanInScope ws = tracer.withSpan(newSpan.start())){
            Thread.sleep(1000L);
            logger.info(" A3:: I'm in the new span doing some cool work that needs its own span");
        } finally {
            newSpan.end();
        }

        logger.info("A4:: I'm in the original span");
    }



}
