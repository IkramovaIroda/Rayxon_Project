package com.company.rayxon.enums;

public enum ProfileOrderStatus {
    ID_ASC("id ASC"),
    ID_DESC("id DESC"),
    NAME_ASC("name ASC"),
    ROLE_ASC("role ASC"),
    ROLE_DESC("role DESC"),
    NAME_DESC("name DESC"),
    EMAIL_ASC("email ASC"),
    EMAIL_DESC("email DESC"),
    STATUS_ASC("status ASC"),
    STATUS_DESC("status DESC"),
    SURNAME_ASC("surname ASC"),
    SURNAME_DESC("surname DESC"),
    CREATED_DATE_ASC("createdDate ASC"),
    CREATED_DATE_DESC("createdDate DESC");

    private String query;

    ProfileOrderStatus(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
