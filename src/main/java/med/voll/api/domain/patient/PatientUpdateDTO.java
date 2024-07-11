package med.voll.api.domain.patient;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDTO;

public record PatientUpdateDTO(@NotNull
                               Long id,
                               @JsonAlias("nome")
                               String name,
                               @JsonAlias("telefone")
                               String phoneNumber,
                               @JsonAlias("endereco")
                               AddressDTO addressDTO) {
}
