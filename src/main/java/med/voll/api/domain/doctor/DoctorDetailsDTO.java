package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

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
