package camp.nextstep.edu.persistsql.step2.query;

import camp.nextstep.edu.persistsql.step2.support.EntitySqlBuilder;

public interface QueryBuilder<T> extends EntitySqlBuilder<T> {

    String buildQuery(Class<T> clazz, WhereClauses whereClauses);
}
