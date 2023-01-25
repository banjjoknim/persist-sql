package camp.nextstep.edu.persistsql.step2.support;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface EntitySqlBuilder<T> {

    default List<String> findColumnNames(Class<T> clazz) {
        List<String> columnNames = Arrays.stream(clazz.getDeclaredFields())
            .filter(field -> field.isAnnotationPresent(Column.class))
            .filter(field -> !field.isAnnotationPresent(Transient.class))
            .map(field -> field.getAnnotation(Column.class))
            .map(Column::name)
            .collect(Collectors.toList());
        return columnNames;
    }
}
