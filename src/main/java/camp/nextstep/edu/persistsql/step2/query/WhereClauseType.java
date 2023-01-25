package camp.nextstep.edu.persistsql.step2.query;

import java.util.function.Function;

public enum WhereClauseType {
    EQUAL(whereClauseValue -> " = " + whereClauseValue.toString()),
    LIKE(whereClauseValue -> " LIKE " + whereClauseValue.toString()),
    IN(whereClauseValues -> " IN (" + whereClauseValues.toString() + ")"),
    NOT_IN(whereClauseValues -> " NOT IN (" + whereClauseValues.toString() + ")");

    private final Function<Object, String> buildWhereClauseFunction;

    WhereClauseType(Function<Object, String> buildWhereClauseFunction) {
        this.buildWhereClauseFunction = buildWhereClauseFunction;
    }

    public String buildWhereQuery(Object value) {
        return this.buildWhereClauseFunction.apply(value);
    }
}
