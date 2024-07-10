package med.voll.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String neighborhood;
    private String zipCode;
    private String number;
    private String complement;
    private String city;
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
}
