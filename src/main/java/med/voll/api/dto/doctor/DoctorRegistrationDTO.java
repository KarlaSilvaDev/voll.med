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
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        @JsonAlias("especialidade")
        Expertise expertise,
        @NotNull
        @Valid
        @JsonAlias("endereco")
        AddressDTO address) {
}
