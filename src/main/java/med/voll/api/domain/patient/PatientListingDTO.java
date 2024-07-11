package med.voll.api.domain.patient;

import com.fasterxml.jackson.annotation.JsonAlias;

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
