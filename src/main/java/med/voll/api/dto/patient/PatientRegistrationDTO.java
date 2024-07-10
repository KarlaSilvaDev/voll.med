package med.voll.api.dto.patient;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.dto.AddressDTO;

public record PatientRegistrationDTO(
        @NotBlank
        @JsonAlias("nome")
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @JsonAlias("telefone")
        String phoneNumber,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        @Valid
        @JsonAlias("endereco")
        AddressDTO address) {
}
