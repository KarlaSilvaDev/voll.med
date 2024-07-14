package med.voll.api.domain.appointment.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.dto.Expertise;

import java.time.LocalDateTime;

public record AppointmentSchedulingDTO(
        @JsonAlias({"idMedico", "id_medico", "doctorId", "doctor_id"})
        Long doctorId,
        @NotNull(message = "{pacientId.required}")
        @JsonAlias({"idPaciente", "id_paciente", "patientId", "patient_id"})
        Long patientId,
        @NotNull(message= "{date.required}")
        @Future(message="{date.invalid}")
        @JsonAlias({"data", "date", "scheduledDate"})
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime scheduledDate,
        @JsonAlias({"expertise", "especialidade"})
        Expertise expertise) {}