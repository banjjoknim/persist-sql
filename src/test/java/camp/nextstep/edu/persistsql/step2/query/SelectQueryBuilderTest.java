package camp.nextstep.edu.persistsql.step2.query;

import camp.nextstep.edu.persistsql.step2.domain.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SelectQueryBuilderTest {

    @DisplayName("요구사항 1 - select 쿼리 만들어보기")
    @Test
    void buildSelectQueryTest() {
        Class<Person> clazz = Person.class;

        SelectQueryBuilder<Person> queryBuilder = new SelectQueryBuilder<>();
        String query = queryBuilder.buildQuery(clazz);

        assertThat(query).isEqualTo("SELECT id, name, age FROM PERSON");
    }
}
