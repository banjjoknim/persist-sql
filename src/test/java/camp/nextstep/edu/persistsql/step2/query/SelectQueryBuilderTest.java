package camp.nextstep.edu.persistsql.step2.query;

import camp.nextstep.edu.persistsql.step2.domain.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SelectQueryBuilderTest {

    @DisplayName("요구사항 1 - select 쿼리 만들어보기")
    @Test
    void buildSelectQueryTest() {
        Class<Person> clazz = Person.class;

        SelectQueryBuilder<Person> queryBuilder = new SelectQueryBuilder<>();
        String query = queryBuilder.buildQuery(clazz, new WhereClauses(Collections.emptyList()));

        assertThat(query).isEqualTo("SELECT id, name, age FROM PERSON");
    }

    @DisplayName("요구사항 1 - select + where equal 쿼리 만들어보기")
    @Test
    void buildSelectWhereQueryTest() {
        Class<Person> clazz = Person.class;

        List<WhereClause> whereClauses = Arrays.asList(new WhereClause("id", 1L, WhereClauseType.EQUAL));
        SelectQueryBuilder<Person> queryBuilder = new SelectQueryBuilder<>();
        String query = queryBuilder.buildQuery(clazz, new WhereClauses(whereClauses));

        assertThat(query).isEqualTo("SELECT id, name, age FROM PERSON WHERE id = 1");
    }

    @DisplayName("요구사항 1 - select + where like 쿼리 만들어보기")
    @Test
    void buildSelectWhereQueryTest2() {
        Class<Person> clazz = Person.class;

        List<WhereClause> whereClauses = Arrays.asList(new WhereClause("name", "%banjjoknim%", WhereClauseType.LIKE));
        SelectQueryBuilder<Person> queryBuilder = new SelectQueryBuilder<>();
        String query = queryBuilder.buildQuery(clazz, new WhereClauses(whereClauses));

        assertThat(query).isEqualTo("SELECT id, name, age FROM PERSON WHERE name LIKE '%banjjoknim%'");
    }

    @DisplayName("요구사항 1 - select + where in 쿼리 만들어보기")
    @Test
    void buildSelectWhereQueryTest3() {
        Class<Person> clazz = Person.class;

        List<WhereClause> whereClauses = Arrays.asList(new WhereClause("id", 1L, WhereClauseType.IN));
        SelectQueryBuilder<Person> queryBuilder = new SelectQueryBuilder<>();
        String query = queryBuilder.buildQuery(clazz, new WhereClauses(whereClauses));

        assertThat(query).isEqualTo("SELECT id, name, age FROM PERSON WHERE id IN (1)");
    }

    @DisplayName("요구사항 1 - select + where not in 쿼리 만들어보기")
    @Test
    void buildSelectWhereQueryTest4() {
        Class<Person> clazz = Person.class;

        List<WhereClause> whereClauses = Arrays.asList(new WhereClause("id", 1L, WhereClauseType.NOT_IN));
        SelectQueryBuilder<Person> queryBuilder = new SelectQueryBuilder<>();
        String query = queryBuilder.buildQuery(clazz, new WhereClauses(whereClauses));

        assertThat(query).isEqualTo("SELECT id, name, age FROM PERSON WHERE id NOT IN (1)");
    }

    @DisplayName("요구사항 1 - select + 다중 where 쿼리 만들어보기")
    @Test
    void buildSelectMultiWhereClauseQueryTest() {
        Class<Person> clazz = Person.class;

        WhereClause idEqualWhereClause = new WhereClause("id", 1L, WhereClauseType.EQUAL);
        WhereClause nameEqualWhereClause = new WhereClause("name", "banjjoknim", WhereClauseType.EQUAL);
        List<WhereClause> whereClauses = new ArrayList<>(Arrays.asList(idEqualWhereClause, nameEqualWhereClause));
        List<WhereOperatorType> whereOperatorTypes = new ArrayList<>(Arrays.asList(WhereOperatorType.AND));
        SelectQueryBuilder<Person> queryBuilder = new SelectQueryBuilder<>();
        String query = queryBuilder.buildQuery(clazz, new WhereClauses(whereClauses, whereOperatorTypes));

        assertThat(query).isEqualTo("SELECT id, name, age FROM PERSON WHERE id = 1 AND name = 'banjjoknim'");
    }
}
