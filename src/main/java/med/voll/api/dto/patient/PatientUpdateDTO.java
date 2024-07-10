package med.voll.api.dto.patient;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.AddressDTO;

public record PatientUpdateDTO(@NotNull
                               Long id,
                               @JsonAlias("nome")
                               String name,
                               @JsonAlias("telefone")
                               String phoneNumber,
                               @JsonAlias("endereco")
                               AddressDTO addressDTO) {
}
