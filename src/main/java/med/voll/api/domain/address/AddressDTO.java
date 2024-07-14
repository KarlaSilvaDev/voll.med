package med.voll.api.domain.address;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @NotBlank(message = "{street.required}")
        @JsonAlias({"logradouro","street"})
        String street,
        @NotBlank(message = "{neighborhood.required}")
        @JsonAlias({"bairro", "neighborhood"})
        String neighborhood,
        @NotBlank(message = "{zipCode.required}")
        @Pattern(regexp = "\\d{8}")
        @JsonAlias({"cep", "zipCode", "zip_code"})
        String zipCode,
        @NotBlank(message = "{city.required}")
        @JsonAlias({"cidade", "city"})
        String city,
        @NotBlank(message = "{uf.required}")
        @JsonAlias({"uf", "estado", "state"})
        String state,
        @JsonAlias({"complemento", "complement"})
        String complement,
        @JsonAlias({"numero", "number"})
        String number) {
}