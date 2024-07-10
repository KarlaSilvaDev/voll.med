package med.voll.api.dto.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.AddressDTO;

public record DoctorUpdateDTO(  @NotNull
                                Long id,
                                @JsonAlias("nome")
                                String name,
                                @JsonAlias("telefone")
                                String phoneNumber,
                                @JsonAlias("endereco")
                                AddressDTO addressDTO) {}