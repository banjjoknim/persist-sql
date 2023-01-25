package camp.nextstep.edu.persistsql.step2.command;

import camp.nextstep.edu.persistsql.step2.support.EntitySqlBuilder;

import java.util.List;

public interface CommandBuilder<T> extends EntitySqlBuilder<T> {

    String buildQuery(Class<T> clazz, List<Object> args);
}
