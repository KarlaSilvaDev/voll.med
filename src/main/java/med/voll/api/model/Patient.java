package med.voll.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.patient.PatientRegistrationDTO;
import med.voll.api.dto.patient.PatientUpdateDTO;

@Entity(name = "Patient")
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotBlank
    @Email
    private String email;
    private String phoneNumber;
    @Column(unique = true)
    private String cpf;
    @Embedded
    private Address address;
    private Boolean active;

    public Patient(PatientRegistrationDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.phoneNumber = data.phoneNumber();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void updateData(PatientUpdateDTO data) {
        if (data.name() != null){
            this.name = data.name();
        }
        if (data.phoneNumber() != null){
            this.phoneNumber = data.phoneNumber();
        }
        if (data.addressDTO() != null){
            this.address.updateAddress(data.addressDTO());
        }
    }

    public void deactivate() {
        this.active = false;
    }
}
