package br.com.fabioalvaro.springbootsleuth.controllers;

import br.com.fabioalvaro.springbootsleuth.clients.CepService;
import br.com.fabioalvaro.springbootsleuth.clients.dao.CepResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController
public class SleuthController {

    private final CepService cepService;

    private Integer userId;


    @Autowired
    private SleuthService sleuthService;

    @Autowired
    private Executor executor;


    Logger logger = LoggerFactory.getLogger(SleuthController.class);

    public SleuthController(CepService cepService) {
        this.cepService = cepService;
        this.userId = 000171;
    }





    @GetMapping("/piu")
   public String helloSleuth(
           @RequestHeader(value = "x-correlation-id", required = false) String xCorrelationId){
        logger.info("Hello Sleuth, piu!");
        logger.info("Service-A is called with Correlation-Id: {}", xCorrelationId);
        MDC.put("correlation.id", xCorrelationId);
        MDC.put("sessao.id", "asdf-qwer-1234-poiu");

        long startTime = System.currentTimeMillis();

        try{
            // Busca o cep
            logger.info("Buscando o cep {}","13070028");
            Thread.sleep(1000L);
            CepResponse retorno = this.cepService.getCep("13070028x");
            logger.info("Retorno cep : {}",retorno);
        }catch(Exception e){
            logger.error("Erro ao buscar CEP {} ",e);
        }
        long endtime = System.currentTimeMillis();
        logger.info("Tempo gasto na chamada remota: " + (endtime-startTime) +"ms");


       logger.info("Final do metodo..");
        MDC.clear();
       return "success PIU";
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


    @GetMapping("/async")
    public String helloSleuthAsync() throws InterruptedException {
        logger.info("Before Async Method Call");
        sleuthService.asyncMethod();
        logger.info("After Async Method Call");

        return "success";
    }






}
