package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DoctorRegistrationDTO;

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
    @Column(unique = true)
    private String crm;
    @Enumerated(EnumType.STRING)
    private Expertise expertise;
    @Embedded
    private Address address;

    public Doctor(DoctorRegistrationDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.expertise = data.expertise();
        this.address = new Address(data.address());
    }
}