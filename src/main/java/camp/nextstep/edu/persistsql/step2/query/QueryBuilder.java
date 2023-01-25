package camp.nextstep.edu.persistsql.step2.query;

import java.util.List;

public interface QueryBuilder<T> {

    public String buildQuery(Class<T> clazz, List<WhereClause> whereClauses);
}
