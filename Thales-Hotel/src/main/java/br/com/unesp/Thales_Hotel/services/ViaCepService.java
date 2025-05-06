package br.com.unesp.Thales_Hotel.services;

import br.com.unesp.Thales_Hotel.domain.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class ViaCepService {

    private final WebClient webClient;

    public ViaCepService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://viacep.com.br/ws").build();
    }

    public ViaCepResponse buscarCep(String cep) {
        try {
            return webClient.get()
                    .uri("/{cep}/json/", cep)
                    .retrieve()
                    .bodyToMono(ViaCepResponse.class)
                    .block();

        } catch (WebClientResponseException e) {
            System.err.println("Error when calling the ViaCep API: " + e.getStatusCode());
            return null;
        } catch (Exception e) {
            System.err.println("Unexpect error: " + e.getMessage());
            return null;
        }
    }

    public static class ViaCepResponse {
        public String cep;
        public String logradouro;
        public String complemento;
        public String bairro;
        public String localidade;
        public String uf;
        public String ibge;
        public String gia;
        public String ddd;
        public String siafi;
    }

    public Address converterViaCepAddress(ViaCepResponse response){
        return new Address(response.cep.replace("-", ""), response.logradouro, response.bairro, response.localidade, response.uf, "Brasil");
    }

    public Address returnAddress(String cep){
        return this.converterViaCepAddress(buscarCep(cep));
    }
}
