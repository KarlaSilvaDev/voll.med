package med.voll.api.dto.patient;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.dto.AddressDTO;

public record PatientRegistrationDTO(
        @NotBlank(message = "{name.required}")
        @JsonAlias("nome")
        String name,
        @NotBlank(message = "{email.required}")
        @Email(message = "{email.invalid}")
        String email,
        @NotBlank(message = "{phoneNumber.required}")
        @JsonAlias("telefone")
        String phoneNumber,
        @NotBlank(message = "{cpf.required}")
        @Pattern(regexp = "\\d{11}", message = "{cpf.invalid}")
        String cpf,
        @NotNull(message = "{address.required}")
        @Valid
        @JsonAlias("endereco")
        AddressDTO address) {
}
