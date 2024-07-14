package med.voll.api.domain.appointment.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"id", "reason"})
public record AppointmentCancellationDTO(
        @NotNull(message = "{appointmentId.required}")
        Long id,
        @NotNull(message = "{cancellationReason.required}")
        @JsonAlias({"motivo", "reason"})
        CancellationReason reason) {}