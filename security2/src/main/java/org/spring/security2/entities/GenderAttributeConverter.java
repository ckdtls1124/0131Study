package org.spring.security2.entities;



import javax.persistence.AttributeConverter;
import javax.persistence.Converter;



@Converter
public class GenderAttributeConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String s) {
        if("male".equals(s)){
            return 1;
        } else if("female".equals(s)){
            return 2;
        }
        return 0;
    }

    @Override
    public String convertToEntityAttribute(Integer s) {
        if("1".equals(s)){
            return "male";
        } else if("2".equals(s)){
            return "female";
        }
        return "ANONYMOUS";
    }


}
