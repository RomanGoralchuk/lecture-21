package by.itacademy.javaenterprise.goralchuk.entity.client;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Client {
    private String name;
    private String surname;
    @Convert(converter = Gender.GenderConverter.class)
    private Gender gender;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Formula(value = "DATEDIFF(CURDATE(),birthday)")
    private Integer age;

    private int getYearsOld(){
        return age/365;
    }

    @Override
    public String toString() {
        return "\n<br>name='" + name +
                "', surname='" + surname +
                "', gender='" + gender.getCode() +
                "', birthday=" + birthday +
                ", age=" + getYearsOld();
    }
}
