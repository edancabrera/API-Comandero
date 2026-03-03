package com.crov.comandero.model.converter;
import com.crov.comandero.model.GiroComercial;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GiroComercialConverter implements AttributeConverter<GiroComercial, String> {

    @Override
    public String convertToDatabaseColumn(GiroComercial attribute) {
        return attribute != null ? attribute.getValorBD() : null;
    }

    @Override
    public GiroComercial convertToEntityAttribute(String dbData) {
        return dbData != null ? GiroComercial.fromValor(dbData) : null;
    }
    
}
