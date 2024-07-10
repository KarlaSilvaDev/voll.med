package med.voll.api.dto.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import med.voll.api.model.Doctor;
import med.voll.api.model.Expertise;

public record DoctorListingDTO (
    Long id,
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
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getExpertise());
    }
}
