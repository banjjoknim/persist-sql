package camp.nextstep.edu.persistsql.step2.command;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

public class InsertQueryBuilder<T> implements CommandBuilder<T> {

    // columns 과 values 를 나누어서 구현해보자
    // values 는 ?(questionMark) 로 대체하자 ex) insert into table (column) values (?)
    @Override
    public String buildQuery(Class<T> clazz, List<Object> args) {
        boolean isEntity = clazz.isAnnotationPresent(Entity.class);
        if (!isEntity) {
            throw new IllegalArgumentException("clazz has not Entity annotation.");
        }

        Table table = clazz.getAnnotation(Table.class);
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + table.name());
        String columnClause = createColumnClause(clazz);
        queryBuilder.append(columnClause);
        String valueClause = createValueClause(args);
        queryBuilder.append(valueClause);

        return queryBuilder.toString();
    }

    private String createColumnClause(Class<T> clazz) {
        StringBuilder columnClauseBuilder = new StringBuilder(" (");
        List<String> columnNames = findColumnNames(clazz);
        columnClauseBuilder.append(String.join(", ", columnNames));
        columnClauseBuilder.append(")");

        return columnClauseBuilder.toString();
    }

    private String createValueClause(List<Object> args) {
        List<String> values = new ArrayList<>();
        for (Object arg : args) {
            if (arg instanceof String) {
                values.add("'" + arg + "'");
                continue;
            }
            if (arg instanceof Number) {
                values.add(arg.toString());
                continue;
            }
            throw new IllegalArgumentException("unsupported value type : " + arg.getClass().getName());
        }

        return " VALUES (" + String.join(", ", values) + ")";
    }
}
