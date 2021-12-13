package by.itacademy.javaenterprise.goralchuk.entity.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

public enum LifeStatus {
    ALIVE("alive"),
    DEAD("dead");
    private final String code;

    LifeStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Converter
    public static class LifeStatusConverter
            implements AttributeConverter<LifeStatus, String> {
        private static final Logger logger = LoggerFactory.getLogger(LifeStatusConverter.class);

        @Override
        public String convertToDatabaseColumn(LifeStatus lifeStatus) {
            if (lifeStatus == null) {
                return null;
            }
            return lifeStatus.getCode();
        }

        @Override
        public LifeStatus convertToEntityAttribute(String string) {
            if (string == null) {
                return null;
            }
            try {
                return Stream.of(LifeStatus.values())
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
