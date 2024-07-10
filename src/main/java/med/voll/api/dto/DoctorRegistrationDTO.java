package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import med.voll.api.model.Expertise;

public record DoctorRegistrationDTO(
        @JsonAlias("nome") String name,
        String email,
        String crm,
        @JsonAlias("especialidade") Expertise expertise,
        @JsonAlias("endereco") AddressDTO address) {
}
