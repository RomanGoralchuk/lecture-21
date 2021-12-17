package by.itacademy.javaenterprise.goralchuk.entity.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
@Entity
@Immutable
@Table(name = "sick_leave")
public class SickLeave extends Document {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numberSickLeave;
    @Convert(converter = Diagnosis.DiagnosisConverter.class)
    private Diagnosis diagnosis;
    private Date startOfTreatment;
    private Date endOfTreatment;
}
