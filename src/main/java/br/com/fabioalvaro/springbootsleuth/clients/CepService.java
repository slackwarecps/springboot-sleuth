package br.com.fabioalvaro.springbootsleuth.clients;

import br.com.fabioalvaro.springbootsleuth.clients.dao.CepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "cepService", url = "https://api.postmon.com.br")
public interface CepService {


    @RequestMapping("/v1/cep/{cep}")
    CepResponse getCep(@PathVariable("cep") String cep) ;


}