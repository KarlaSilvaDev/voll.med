package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.doctor.dto.DoctorRegistrationDTO;
import med.voll.api.domain.doctor.dto.DoctorUpdateDTO;
import med.voll.api.domain.doctor.dto.Expertise;

@Entity(name="Doctor")
@Table(name="doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    @Column(unique = true)
    private String crm;
    @Enumerated(EnumType.STRING)
    private Expertise expertise;
    @Embedded
    private Address address;
    private Boolean active;

    public Doctor(DoctorRegistrationDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.phoneNumber = data.phoneNumber();
        this.crm = data.crm();
        this.expertise = data.expertise();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void updateData(DoctorUpdateDTO data) {
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