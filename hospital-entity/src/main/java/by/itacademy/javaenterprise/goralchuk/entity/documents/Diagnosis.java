package by.itacademy.javaenterprise.goralchuk.entity.documents;

import by.itacademy.javaenterprise.goralchuk.entity.client.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

public enum Diagnosis {
    HEADACHE("headache"),
    INTOXICATION("intoxication"),
    COLDS("colds"),
    COVID("covid"),
    DERANGEMENT("derangement");
    private final String code;

    Diagnosis(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Converter(autoApply = true)
    public static class DiagnosisConverter
            implements AttributeConverter<Diagnosis, String> {
        private static final Logger logger = LoggerFactory.getLogger(DiagnosisConverter.class);

        @Override
        public String convertToDatabaseColumn(Diagnosis diagnosis) {
            if (diagnosis == null) {
                return null;
            }
            return diagnosis.getCode();
        }

        @Override
        public Diagnosis convertToEntityAttribute(String string) {
            if (string == null) {
                return null;
            }
            try {
                return Stream.of(Diagnosis.values())
                        .filter(el -> el.getCode().equals(string))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Bad argument converter"));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return null;
            }
        }
    }

}
