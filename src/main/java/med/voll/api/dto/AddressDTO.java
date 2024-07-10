package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AddressDTO(
        @JsonAlias("logradouro") String street,
        @JsonAlias("bairro") String neighborhood,
        @JsonAlias("cep") String zipCode,
        @JsonAlias("cidade") String city,
        @JsonAlias("uf") String state,
        @JsonAlias("complemento") String complement,
        @JsonAlias("numero") String number) {
}
