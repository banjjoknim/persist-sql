package camp.nextstep.edu.persistsql.step2.command;

import camp.nextstep.edu.persistsql.step2.domain.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class InsertQueryBuilderTest {

    @DisplayName("요구사항 2 - insert 쿼리 만들어보기")
    @Test
    void buildInsertQueryTest() {
        Class<Person> clazz = Person.class;

        InsertQueryBuilder<Person> queryBuilder = new InsertQueryBuilder<>();
        String query = queryBuilder.buildQuery(clazz, Arrays.asList(1L, "banjjoknim", 30));

        assertThat(query).isEqualTo("INSERT INTO PERSON (id, name, age) VALUES (1, 'banjjoknim', 30)");
    }
}
