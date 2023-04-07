package com.bgpark.musinsa.domain.common;

import com.bgpark.musinsa.security.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Convert(converter = StringEncryptConverter.class)이 선언된 엔티티의 문자열 필드는
 * 데이터베이스에 저장 시 인코딩, 데이터베이스에서 조회 시 디코딩 된다
 * @author 박병길
 */
@Converter
@Component
@RequiredArgsConstructor
public class StringEncryptConverter implements AttributeConverter<String, String> {

    private final Encryptor encryptor;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptor.decrypt(dbData);
    }
}
