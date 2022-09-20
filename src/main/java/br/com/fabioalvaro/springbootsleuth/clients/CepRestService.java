package br.com.fabioalvaro.springbootsleuth.clients;

import br.com.fabioalvaro.springbootsleuth.clients.dao.CepResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/v1/ceps")
public class CepRestService {


    private final CepService cepService;

    private final Environment env;

    private static final Logger log = LoggerFactory.getLogger(CepRestService.class);

    public CepRestService(CepService cepService, Environment env) {



        this.cepService = cepService;

        this.env = env;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{cep}", method = RequestMethod.GET)
    public CepResponse getCep(@PathVariable String cep) {

        CepResponse retorno =  cepService.getCep(cep);

        log.info("Retorno do CEP : "+ retorno);



        return retorno;
    }


    @GetMapping("/envdetails")
    public String envDetails (){



        String FABIO = ( env.getProperty("FABIO") == null) ? "NULL" : env.getProperty("FABIO");

        System.out.println(FABIO);

        log.info("Constructor CEP JAVA_HOME {}", env.getProperty("JAVA_HOME"));





        return env.toString();
    }

}