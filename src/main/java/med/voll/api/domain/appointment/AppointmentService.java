package med.voll.api.domain.appointment;

import med.voll.api.domain.DataValidationException;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void schedule(AppointmentSchedulingDTO data){
        if(!patientRepository.existsById(data.patientId())){
            throw new DataValidationException("O id do paciente informado não existe.");
        }

        if(data.doctorId() != null && !doctorRepository.existsById(data.doctorId())){
            throw new DataValidationException("O id do médico informado não existe.");
        }
        var patient = patientRepository.getReferenceById(data.patientId());
        var doctor = chooseDoctor(data);
        var appointment = new Appointment(null, doctor, patient, data.scheduledDate(),null);

        appointmentRepository.save(appointment);
    }

    private Doctor chooseDoctor(AppointmentSchedulingDTO data) {
        if (data.doctorId() != null){
            return doctorRepository.getReferenceById(data.doctorId());
        }

        if (data.expertise() == null){
            throw new DataValidationException("É obrigatório informar a especialidade quando um médico não for escolhido!");
        }

        return doctorRepository.findRandomAvailableDoctorByDate(data.expertise(), data.scheduledDate());


    }

    public void cancel(AppointmentCancellationDTO data) {
        if (!appointmentRepository.existsById(data.id())){
            throw new DataValidationException("O id da consulta informado não existe.");
        }

        var appointment = appointmentRepository.getReferenceById(data.id());
        appointment.cancel(data.reason());
    }
}
