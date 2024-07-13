package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailsDTO(Long id, Long doctorId, Long patientId, LocalDateTime scheduledDate) {
    public AppointmentDetailsDTO(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getScheduledDate());
    }
}
