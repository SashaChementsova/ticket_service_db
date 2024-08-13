package org.chementsova.model;


public enum TicketType {
    BUS("bus"),
    PLANE("plane"),
    TRAIN("train"),
    SHIP("ship"),
    EVENT("event");

    private String value;

    TicketType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
