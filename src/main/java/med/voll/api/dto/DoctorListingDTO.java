package med.voll.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.Doctor;
import med.voll.api.model.Expertise;

public record DoctorListingDTO (
    @JsonProperty("nome")
    String name,
    @JsonProperty("email")
    String email,
    @JsonProperty("crm")
    String crm,
    @JsonProperty("especialidade")
    Expertise expertise)
{
    public DoctorListingDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getExpertise());
    }
}
