package med.voll.api.domain.patient.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDTO;

public record PatientUpdateDTO(
        @NotNull(message = "{patientId.required}")
        Long id,
        @JsonAlias({"nome", "name"})
        String name,
        @JsonAlias({"telefone", "phoneNumber", "phone_number"})
        String phoneNumber,
        @JsonAlias({"endereco", "address"})
        AddressDTO addressDTO) {}