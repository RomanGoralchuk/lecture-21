package by.itacademy.javaenterprise.goralchuk.entity.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private Long zipCode;
    private String city;
    private String street;
    private Integer houseNumber;
    private Integer apartmentNumber;

    @Override
    public String toString() {
        return "Address{" +
                "post code=" + zipCode +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", apartmentNumber=" + apartmentNumber +
                '}';
    }
}
