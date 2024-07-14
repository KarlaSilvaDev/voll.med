package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.dto.AppointmentSchedulingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorScheduleConflictValidator implements AppointmentSchedulingValidator{

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentSchedulingDTO data){
        var doctorScheduleConflict = repository.existsByDoctorIdAndScheduledDateAndReasonIsNull(data.doctorId(), data.scheduledDate());

        if(doctorScheduleConflict){
            throw new DataValidationException("O médico informado já possui outra consulta agendada nesse mesmo horário.");
        }
    }
}