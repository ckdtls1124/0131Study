package org.spring.security2.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.security.auth.kerberos.EncryptionKey;

@Converter
@RequiredArgsConstructor
public class PasswordEnDecodeConverter implements AttributeConverter<String, String> {
    private final PasswordEncoder passwordEncoder;
    @Override
    public String convertToDatabaseColumn(String attribute) {
        String encoded=passwordEncoder.encode(attribute);
        return encoded;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {

        return null;
    }
}
