package pers.xkr.persistence.pojo;

public class MappedStatement {
    private String namespace;
    private String id;
    private String sql;
    private String parameterType;
    private String resultType;
    private String opertType;


    public String getOpertType() {
        return opertType;
    }

    public void setOpertType(String opertType) {
        this.opertType = opertType;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
