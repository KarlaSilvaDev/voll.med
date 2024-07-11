package med.voll.api.domain.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

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
