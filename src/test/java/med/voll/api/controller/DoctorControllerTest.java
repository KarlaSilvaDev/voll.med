package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressDTO;
import med.voll.api.domain.appointment.dto.AppointmentSchedulingDTO;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.doctor.DoctorService;
import med.voll.api.domain.doctor.dto.DoctorDetailsDTO;
import med.voll.api.domain.doctor.dto.DoctorRegistrationDTO;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DoctorRegistrationDTO> doctorRegistrationJson;

    @Autowired
    private JacksonTester<DoctorDetailsDTO> doctorDetailsJson;

    @MockBean
    private DoctorService doctorService;

    @Test
    @DisplayName("Deveria devolver código HTTP 400 quando as informações são inválidas.")
    @WithMockUser
    void registerScenario1() throws Exception {
        var response = mvc
                .perform(post("/doctors"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código HTTP 200 quando as informações são válidas.")
    @WithMockUser
    void registerScenario2() throws Exception {
        var doctorRegistrationData = new DoctorRegistrationDTO(
                "Doctor",
                "doctor@voll.med",
                "99999999999",
                "111111",
                Expertise.CARDIOLOGIA,
                addresData()
        );

        var doctorDetails = new DoctorDetailsDTO(
                null,
                doctorRegistrationData.name(),
                doctorRegistrationData.email(),
                doctorRegistrationData.phoneNumber(),
                doctorRegistrationData.crm(),
                doctorRegistrationData.expertise(),
                new Address(doctorRegistrationData.address())
        );

        when(doctorService.register(any())).thenReturn(doctorDetails);

        var response = mvc
                .perform(
                        post("/doctors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(doctorRegistrationJson
                                        .write(doctorRegistrationData)
                                        .getJson())
                )
                .andReturn()
                .getResponse();

        var expectedJson = doctorDetailsJson
                .write(doctorDetails)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
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