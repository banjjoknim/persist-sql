package camp.nextstep.edu.persistsql.step2.query;

public enum WhereOperatorType {
    AND(" AND "),
    OR(" OR "),
    NOT(" NOT ");

    private final String value;

    WhereOperatorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
