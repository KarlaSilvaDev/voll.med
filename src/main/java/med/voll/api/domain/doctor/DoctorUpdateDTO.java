package med.voll.api.domain.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDTO;

public record DoctorUpdateDTO(  @NotNull
                                Long id,
                                @JsonAlias("nome")
                                String name,
                                @JsonAlias("telefone")
                                String phoneNumber,
                                @JsonAlias("endereco")
                                AddressDTO addressDTO) {}