package camp.nextstep.edu.persistsql.step2.query;

public interface QueryBuilder<T> {

    public String buildQuery(Class<T> clazz);
}
