package med.voll.api.domain.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "{login.required}")
        String login,
        @NotBlank(message = "{password.required}")
        @JsonAlias("senha")
        String password) {
}
