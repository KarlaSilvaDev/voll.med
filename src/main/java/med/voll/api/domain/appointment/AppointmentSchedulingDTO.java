package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Expertise;

import java.time.LocalDateTime;

public record AppointmentSchedulingDTO(@JsonProperty("idMedico")
                                       Long doctorId,
                                       @NotNull(message = "{pacientId.required}")
                                       @JsonProperty("idPaciente")
                                       Long patientId,
                                       @NotNull(message= "{date.required}")
                                       @Future(message="{date.invalid}")
                                       @JsonProperty("data")
                                       @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                                       LocalDateTime scheduledDate,
                                       Expertise expertise) {}