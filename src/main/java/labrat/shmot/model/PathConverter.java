package labrat.shmot.model;

import javax.persistence.AttributeConverter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathConverter implements AttributeConverter<Path,String> {
    @Override
    public String convertToDatabaseColumn(Path attribute) {
        if(attribute==null) return null;
        return attribute.toString();
    }

    @Override
    public Path convertToEntityAttribute(String dbData) {
        if(dbData==null) return null;
        return Paths.get(dbData);
    }
}
