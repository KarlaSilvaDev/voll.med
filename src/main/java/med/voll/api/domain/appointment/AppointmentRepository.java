package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndScheduledDateAndReasonIsNull(Long doctorId, LocalDateTime localDateTime);

    boolean existsByPatientIdAndScheduledDateBetweenAndReasonIsNull(Long patientId, LocalDateTime startTime, LocalDateTime endTime);
}
