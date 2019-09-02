package labrat.shmot.model;

import javax.persistence.AttributeConverter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathConverter implements AttributeConverter<Path,String> {
    @Override
    public String convertToDatabaseColumn(Path attribute) {
        return attribute.toString();
    }

    @Override
    public Path convertToEntityAttribute(String dbData) {
        return Paths.get(dbData);
    }
}
