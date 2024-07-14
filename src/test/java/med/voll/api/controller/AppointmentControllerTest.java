package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentService;
import med.voll.api.domain.appointment.dto.AppointmentDetailsDTO;
import med.voll.api.domain.appointment.dto.AppointmentSchedulingDTO;
import med.voll.api.domain.doctor.dto.Expertise;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE_TIME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentSchedulingDTO> appointmentSchedulingJson;

    @Autowired
    private JacksonTester<AppointmentDetailsDTO> appointmentDetailsJson;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    @DisplayName("Deveria devolver código HTTP 400 quando as informações são inválidas.")
    @WithMockUser
    void scheduleAppointmentScenario1() throws Exception {
        var response = mvc
                .perform(post("/appointments"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código HTTP 200 quando as informações são válidas.")
    @WithMockUser
    void scheduleAppointmentScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var expertise = Expertise.CARDIOLOGIA;
        var appointmentDetails = new AppointmentDetailsDTO(null, 2l, 5l, date);

        when(appointmentService.schedule(any())).thenReturn(appointmentDetails);

        var response = mvc
                .perform(
                        post("/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(appointmentSchedulingJson
                                        .write(new AppointmentSchedulingDTO(2l, 5l, date, expertise))
                                        .getJson())
                )
                .andReturn()
                .getResponse();

        var expectedJson = appointmentDetailsJson
                .write(appointmentDetails)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}