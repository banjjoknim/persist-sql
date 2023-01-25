package camp.nextstep.edu.persistsql.step2.query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelectQueryBuilder<T> implements QueryBuilder<T> {

    @Override
    public String buildQuery(Class<T> clazz) {
        boolean isEntity = clazz.isAnnotationPresent(Entity.class);
        if (!isEntity) {
            throw new IllegalArgumentException("clazz has not Entity annotation.");
        }

        Table table = clazz.getAnnotation(Table.class);
        List<String> columnNames = Arrays.stream(clazz.getDeclaredFields())
            .filter(field -> field.isAnnotationPresent(Column.class))
            .filter(field -> !field.isAnnotationPresent(Transient.class))
            .map(field -> field.getAnnotation(Column.class))
            .map(Column::name)
            .collect(Collectors.toList());

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT ");
        queryBuilder.append(String.join(", ", columnNames));
        queryBuilder.append(" FROM ");
        queryBuilder.append(table.name());

        return queryBuilder.toString();
    }
}
