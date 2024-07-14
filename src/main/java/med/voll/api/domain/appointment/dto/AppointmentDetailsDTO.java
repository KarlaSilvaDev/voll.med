package med.voll.api.domain.appointment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import med.voll.api.domain.appointment.Appointment;

import java.time.LocalDateTime;

@JsonPropertyOrder({"id", "doctorId", "patientId", "scheduledDate"})
public record AppointmentDetailsDTO(
        Long id,
        @JsonProperty("idMedico")
        Long doctorId,
        @JsonProperty("idPaciente")
        Long patientId,
        @JsonProperty("data")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime scheduledDate)
{
    public AppointmentDetailsDTO(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getScheduledDate());
    }
}
