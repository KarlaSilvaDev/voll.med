package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressDTO;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.doctor.dto.DoctorRegistrationDTO;
import med.voll.api.domain.doctor.dto.Expertise;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.dto.PatientRegistrationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deveria devolver null quando único medico cadastrado não está disponível na data")
    void findRandomAvailableDoctorByDateScenario1() {
        //given or arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = registerDoctor("Doctor", "doctor@voll.med", "123456", Expertise.CARDIOLOGIA);
        var patient = registerPatient("Patient", "patient@gmail.com", "00000000000");
        scheduleAppointment(doctor, patient, nextMondayAt10);

        //when or act
        var availableDoctor = doctorRepository.findRandomAvailableDoctorByDate(Expertise.CARDIOLOGIA, nextMondayAt10);

        //then or assert
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Deveria devolver um médico quando houver algum médico disponível na data")
    void findRandomAvailableDoctorByDateScenario2() {
        //given or arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = registerDoctor("Doctor", "doctor@voll.med", "123456", Expertise.CARDIOLOGIA);

        //when or act
        var availableDoctor = doctorRepository.findRandomAvailableDoctorByDate(Expertise.CARDIOLOGIA, nextMondayAt10);

        //then or assert
        assertThat(availableDoctor).isEqualTo(doctor);
    }

    @Test
    @DisplayName("Deveria devolver null quando não há médicos cadastrados na especialidade informada")
    void findRandomAvailableDoctorByDateScenario3(){
        //given or arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor1 = registerDoctor("Doctor1", "doctor1@voll.med", "123456", Expertise.CARDIOLOGIA);
        var doctor2 = registerDoctor("Doctor2", "doctor2@voll.med", "789101", Expertise.CARDIOLOGIA);

        //when or act
        var availableDoctor = doctorRepository.findRandomAvailableDoctorByDate(Expertise.ORTOPEDIA, nextMondayAt10);

        //then or assert
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Deveria devolver um médico aleatório quando houver vários médicos livres na data")
    void findRandomAvailableDoctorByDateScenario4() {
        //given or arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor1 = registerDoctor("Doctor 1", "doctor1@voll.med", "123456", Expertise.CARDIOLOGIA);
        var doctor2 = registerDoctor("Doctor 2", "doctor2@voll.med", "789012", Expertise.CARDIOLOGIA);
        var doctor3 = registerDoctor("Doctor 3", "doctor3@voll.med", "345678", Expertise.CARDIOLOGIA);
        var doctor4 = registerDoctor("Doctor 4", "doctor4@voll.med", "345633", Expertise.CARDIOLOGIA);

        List<Doctor> availableDoctors = Arrays.asList(doctor1, doctor2, doctor3, doctor4);
        List<Doctor> returnedDoctors = new ArrayList<>();

        //when or act
        for (int i = 0; i < 100; i++) {
            var availableDoctor = doctorRepository.findRandomAvailableDoctorByDate(Expertise.CARDIOLOGIA, nextMondayAt10);
            returnedDoctors.add(availableDoctor);
        }

        //then or assert
        assertThat(returnedDoctors).containsAll(availableDoctors);
    }

    private void scheduleAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        entityManager.persist(new Appointment(null, doctor, patient, date,null));
    }

    private Doctor registerDoctor(String name, String email, String crm, Expertise expertise) {
        var doctor = new Doctor(doctorData(name, email, crm, expertise));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(patientData(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private DoctorRegistrationDTO doctorData(String name, String email, String crm, Expertise expertise) {
        return new DoctorRegistrationDTO(
                name,
                email,
                "61999999999",
                crm,
                expertise,
                addresData()
        );
    }

    private PatientRegistrationDTO patientData(String name, String email, String cpf) {
        return new PatientRegistrationDTO(
                name,
                email,
                "61999999999",
                cpf,
                addresData()
        );
    }

    private AddressDTO addresData() {
        return new AddressDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}