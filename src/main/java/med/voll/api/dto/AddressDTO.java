package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @NotBlank
        @JsonAlias("logradouro")
        String street,
        @NotBlank
        @JsonAlias("bairro")
        String neighborhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        @JsonAlias("cep")
        String zipCode,
        @NotBlank
        @JsonAlias("cidade")
        String city,
        @NotBlank
        @JsonAlias("uf")
        String state,
        @JsonAlias("complemento")
        String complement,
        @JsonAlias("numero")
        String number) {
}
