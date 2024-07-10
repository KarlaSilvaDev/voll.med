package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateDTO(@NotNull
                               Long id,
                               @JsonAlias("nome")
                               String name,
                               @JsonAlias("telefone")
                               String phoneNumber,
                               @JsonAlias("endereco")
                               AddressDTO addressDTO) {
}
