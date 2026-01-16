package com.crov.comandero.model.converter;

import com.crov.comandero.model.Impuesto;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class ImpuestoConverter implements AttributeConverter<Impuesto, String>{

    @Override
    public String convertToDatabaseColumn(Impuesto attribute) {
        return attribute != null ? attribute.getDbValue(): null;
    }

    @Override
    public Impuesto convertToEntityAttribute(String dbData) {
        return dbData != null ? Impuesto.fromDbValue(dbData): null;
    }

}
