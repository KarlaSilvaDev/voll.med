package med.voll.api.domain.patient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.patient.Patient;

@JsonPropertyOrder({"id", "name", "email", "phoneNumber", "cpf", "address", "active"})
public record PatientDetailsDTO(
        Long id,
        @JsonProperty("nome")
        String name,
        String email,
        @JsonProperty("telefone")
        String phoneNumber,
        String cpf,
        @JsonProperty("endereco")
        Address address,
        @JsonProperty("ativo")
        Boolean active)
{
    public PatientDetailsDTO(Patient patient) {
        this(patient.getId(),
             patient.getName(),
             patient.getEmail(),
             patient.getPhoneNumber(),
             patient.getCpf(),
             patient.getAddress(),
             patient.getActive());
    }
}
