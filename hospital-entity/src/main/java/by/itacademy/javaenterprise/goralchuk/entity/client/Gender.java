package by.itacademy.javaenterprise.goralchuk.entity.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

public enum Gender {
    MALE("male"),
    FEMALE("female"),
    INTERSEX("intersex"),
    TRANSGENDER("transgender");
    private final String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Converter
    public static class GenderConverter
            implements AttributeConverter<Gender, String> {
        private static final Logger logger = LoggerFactory.getLogger(GenderConverter.class);

        @Override
        public String convertToDatabaseColumn(Gender gender) {
            if (gender == null) {
                return null;
            }
            return gender.getCode();
        }

        @Override
        public Gender convertToEntityAttribute(String string) {
            if (string == null) {
                return null;
            }
            try {
                return Stream.of(Gender.values())
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
