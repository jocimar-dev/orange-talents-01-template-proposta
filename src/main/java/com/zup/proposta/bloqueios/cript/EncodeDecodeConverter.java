package com.zup.proposta.bloqueios.cript;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EncodeDecodeConverter implements AttributeConverter<String, String> {

    private final EncodeDecode encodeDecode;

    public EncodeDecodeConverter(EncodeDecode encodeDecode) {
        this.encodeDecode = encodeDecode;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encodeDecode.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encodeDecode.decode(dbData);
    }

}
