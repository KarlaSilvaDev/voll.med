package med.voll.api.domain.appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.appointment.dto.CancellationReason;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Entity(name = "Appointment")
@Table(name = "appointments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private LocalDateTime scheduledDate;
    @Column(name = "cancellation_reason")
    @Enumerated(EnumType.STRING)
    private CancellationReason reason;

    public void cancel(CancellationReason reason) {
        this.reason = reason;
    }
}