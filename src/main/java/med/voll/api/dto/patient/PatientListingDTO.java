package med.voll.api.dto.patient;

import com.fasterxml.jackson.annotation.JsonAlias;
import med.voll.api.model.Patient;

public record PatientListingDTO(
        Long id,
        @JsonAlias("nome")
        String name,
        String email,
        String cpf
) {
    public PatientListingDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
