package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancellationDTO(
        @NotNull(message = "{appointmentId.required}")
        Long id,
        @NotNull(message = "{cancellationReason.required")
        CancellationReason reason
) {
}
