package med.voll.api.dto.doctor;

import med.voll.api.model.Address;
import med.voll.api.model.Doctor;
import med.voll.api.model.Expertise;

public record DoctorDetailsDTO(Long id, String name, String email, String phoneNumber, String crm, Expertise expertise, Address address) {
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
