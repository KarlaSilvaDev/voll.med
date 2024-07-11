package med.voll.api.dto.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.dto.AddressDTO;
import med.voll.api.model.Expertise;

public record DoctorRegistrationDTO(
        @NotBlank(message = "{name.required}")
        @JsonAlias("nome")
        String name,
        @NotBlank(message = "{email.required}")
        @Email(message = "{email.invalid}")
        String email,
        @NotBlank(message = "{phoneNumber.required}")
        @JsonAlias("telefone")
        String phoneNumber,
        @NotBlank(message = "{crm.required}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalid}")
        String crm,
        @NotNull(message = "{expertise.required}")
        @JsonAlias("especialidade")
        Expertise expertise,
        @NotNull(message = "{address.required}")
        @Valid
        @JsonAlias("endereco")
        AddressDTO address) {
}
