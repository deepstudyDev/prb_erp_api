package com.prb.erp.procedure.inter;

public enum FroebelInterfaceEnum {

    SOCICAL_SECRET_NUMBER("0000000000000"),
    DEFAULT_INSERT_ID("MINDEDU"),
    DEFAULT_CHILD_BIRTH_DAY("000101");

    String property;

    FroebelInterfaceEnum(String property) {
        this.property = property;
    }

    public String getProperty() {
        return this.property;
    }
}
