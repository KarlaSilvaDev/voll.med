package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pagination);

    @Query("""
               SELECT d FROM Doctor d
               WHERE 
               d.active = true 
               AND 
               d.expertise = :expertise 
               AND 
               d.id NOT IN(SELECT a.doctor.id FROM Appointment a WHERE a.scheduledDate = :scheduledDate)
               ORDER BY rand() 
               LIMIT 1
           """)
    Doctor findRandomAvailableDoctorByDate(Expertise expertise, LocalDateTime scheduledDate);
}
