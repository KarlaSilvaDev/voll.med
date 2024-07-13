package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements AppointmentSchedulingValidator {

    @Autowired
    private DoctorRepository repository;

    public void validate(AppointmentSchedulingDTO data){
        if (data.doctorId() == null){
            return;
        }
        System.out.println(data);

        var doctorIsActive = repository.findActiveById(data.doctorId());

        if (!doctorIsActive){
            throw new DataValidationException("A consulta não pode ser agendada com um médico inativo.");
        }
    }
}
