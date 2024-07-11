package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record PatientDetailsDTO(Long id, String name, String email, String phoneNumber, String cpf, Address address, Boolean active){
    public PatientDetailsDTO(Patient patient) {
        this(patient.getId(),
             patient.getName(),
             patient.getEmail(),
             patient.getPhoneNumber(),
             patient.getCpf(),
             patient.getAddress(),
             patient.getActive());
    }
}
