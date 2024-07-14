package med.voll.api.domain.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"street", "number", "neighborhood", "city", "state", "complement", "zipCode" })
public class Address {
    @JsonProperty("logradouro")
    private String street;
    @JsonProperty("bairro")
    private String neighborhood;
    @JsonProperty("cep")
    private String zipCode;
    @JsonProperty("numero")
    private String number;
    @JsonProperty("complemento")
    private String complement;
    @JsonProperty("cidade")
    private String city;
    @JsonProperty("uf")
    private String state;

    public Address(AddressDTO address) {
        this.street = address.street();
        this.neighborhood = address.neighborhood();
        this.zipCode = address.zipCode();
        this.number = address.number();
        this.complement = address.complement();
        this.city = address.city();
        this.state = address.state();
    }

    public void updateAddress(AddressDTO data) {
        if (data.street() != null) {
            this.street = data.street();
        }
        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }
        if (data.zipCode() != null) {
            this.zipCode = data.zipCode();
        }
        if (data.state() != null) {
            this.state = data.state();
        }
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.number() != null) {
            this.number = data.number();
        }
        if (data.complement() != null) {
            this.complement = data.complement();
        }
    }
}