package com.aits.Safe.Locker.entity;

import jakarta.persistence.*;

@Entity
public class WorkItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private long cost;

    @Enumerated(EnumType.STRING)
    private Type type;

    public WorkItem() {
    }

    public WorkItem(Long id, String name, long cost, Type type) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        CAR,
        BIKE
    }
}
