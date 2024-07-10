package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import jakarta.persistence.Column;
import med.voll.api.model.Doctor;
import med.voll.api.model.Expertise;

public record DoctorListingDTO (
    @JsonAlias("nome")
    String name,
    @JsonAlias("email")
    String email,
    @JsonProperty("crm")
    String crm,
    @JsonAlias("especialidade")
    Expertise expertise)
{
    public DoctorListingDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getExpertise());
    }
}
