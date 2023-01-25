package camp.nextstep.edu.persistsql.step2.query;

public class WhereClause {

    private final String fieldName;
    private final Object value;
    private final WhereClauseType clauseType;

    public WhereClause(String fieldName, Object value, WhereClauseType clauseType) {
        this.fieldName = fieldName;
        this.value = value;
        this.clauseType = clauseType;
    }

    public String createWhereQuery() {
        StringBuilder whereQuery = new StringBuilder(fieldName);
        if (value instanceof String) {
            return whereQuery.append(clauseType.buildWhereQuery("'" + value + "'")).toString();
        }
        if (value instanceof Number) {
            return whereQuery.append(clauseType.buildWhereQuery(value)).toString();
        }
        throw new IllegalArgumentException("unsupported value type : " + value.getClass().getName());
    }

    public String getFieldName() {
        return fieldName;
    }
}