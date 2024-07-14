package med.voll.api.domain.doctor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import med.voll.api.domain.doctor.Doctor;

@JsonPropertyOrder({"id", "name", "email", "crm", "expertise"})
public record DoctorListingDTO (
        Long id,
        @JsonProperty("nome")
        String name,
        String email,
        String crm,
        @JsonProperty("especialidade")
        Expertise expertise)
{
    public DoctorListingDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getExpertise());
    }
}