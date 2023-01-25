package camp.nextstep.edu.persistsql.step2.query;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WhereClauses {

    private final List<WhereClause> whereClauses;
    private final List<WhereOperatorType> operatorTypes;

    public WhereClauses() {
        this(Collections.emptyList(), Collections.emptyList());
    }

    public WhereClauses(List<WhereClause> whereClauses) {
        this(whereClauses, Collections.emptyList());
    }

    public WhereClauses(List<WhereClause> whereClauses, List<WhereOperatorType> operatorTypes) {
        this.whereClauses = whereClauses;
        this.operatorTypes = operatorTypes;
    }

    public boolean isEmpty() {
        return this.whereClauses.isEmpty();
    }

    public List<String> getFieldNames() {
        return this.whereClauses.stream()
            .map(WhereClause::getFieldName)
            .collect(Collectors.toList());
    }

    public String buildWhereQuery() {
        List<String> whereQueries = whereClauses.stream()
            .map(WhereClause::createWhereQuery)
            .collect(Collectors.toList());
        return joinWhereQuery(whereQueries);
    }

    private String joinWhereQuery(List<String> whereQueries) {
        StringBuilder whereQueryBuilder = new StringBuilder(" WHERE ");
        while (!whereQueries.isEmpty()) {
            joinWhereQuery(whereQueries, whereQueryBuilder);
            joinOperator(whereQueryBuilder);
        }
        return whereQueryBuilder.toString();
    }

    private void joinWhereQuery(List<String> whereQueries, StringBuilder whereQueryBuilder) {
        if (!whereQueries.isEmpty()) {
            String whereQuery = whereQueries.remove(0);
            whereQueryBuilder.append(whereQuery);
        }
    }

    private void joinOperator(StringBuilder whereQueryBuilder) {
        if (!operatorTypes.isEmpty()) {
            WhereOperatorType operatorType = operatorTypes.remove(0);
            whereQueryBuilder.append(operatorType.getValue());
        }
    }
}
