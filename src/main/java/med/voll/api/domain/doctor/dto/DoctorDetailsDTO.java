package med.voll.api.domain.doctor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.doctor.Doctor;

@JsonPropertyOrder({"id", "name", "email", "phoneNumber", "crm", "expertise", "address"})
public record DoctorDetailsDTO(
        Long id,
        @JsonProperty("nome")
        String name,
        String email,
        @JsonProperty("telefone")
        String phoneNumber,
        String crm,
        @JsonProperty("especialidade")
        Expertise expertise,
        @JsonProperty("endereco")
        Address address)
{
    public DoctorDetailsDTO(Doctor doctor){
            this(doctor.getId(),
                 doctor.getName(),
                 doctor.getEmail(),
                 doctor.getPhoneNumber(),
                 doctor.getCrm(),
                 doctor.getExpertise(),
                 doctor.getAddress()
        );
    }
}