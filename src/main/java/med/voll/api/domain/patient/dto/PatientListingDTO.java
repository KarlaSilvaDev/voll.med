package med.voll.api.domain.patient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import med.voll.api.domain.patient.Patient;

@JsonPropertyOrder({"id", "name", "email", "cpf"})
public record PatientListingDTO(
        Long id,
        @JsonProperty("nome")
        String name,
        String email,
        String cpf)
{
    public PatientListingDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
