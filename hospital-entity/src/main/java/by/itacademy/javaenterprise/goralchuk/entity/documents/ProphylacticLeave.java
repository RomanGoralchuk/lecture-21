package by.itacademy.javaenterprise.goralchuk.entity.documents;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ToString(callSuper=true)
@Entity
@Table(name = "prophylactic_leave")
public class ProphylacticLeave extends Document {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "idCard"),
            strategy = "by.itacademy.javaenterprise.goralchuk.generatorid.IdGenerator")
    private String numberProphylacticLeave;
    private String description;
}
