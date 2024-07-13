package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements AppointmentSchedulingValidator {

    @Autowired
    private PatientRepository repository;

    public void validate(AppointmentSchedulingDTO data){

        var patientIsActive = repository.findActiveById(data.patientId());

        if (!patientIsActive){
            throw new DataValidationException("A consulta não pode ser agendada para um paciente inativo.");
        }
    }
}
