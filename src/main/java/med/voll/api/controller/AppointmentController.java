package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.dto.AppointmentCancellationDTO;
import med.voll.api.domain.appointment.dto.AppointmentSchedulingDTO;
import med.voll.api.domain.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping
    @Transactional
    public ResponseEntity scheduleAppointment(@RequestBody @Valid AppointmentSchedulingDTO data){
        var dto = service.schedule(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelAppointment(@RequestBody @Valid AppointmentCancellationDTO data){
        service.cancel(data);
        return ResponseEntity.noContent().build();
    }
}