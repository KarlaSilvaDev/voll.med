package med.voll.api.domain.appointment.validations.cancellation;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.appointment.dto.AppointmentCancellationDTO;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppointmentEarlyCancellationValidator implements AppointmentCancellationValidator{

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(AppointmentCancellationDTO data) {
        var appointment = repository.getReferenceById(data.id());
        var now = LocalDateTime.now();
        var hourDifference = Duration.between(now, appointment.getScheduledDate()).toHours();

        if (hourDifference < 24) {
            throw new DataValidationException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
