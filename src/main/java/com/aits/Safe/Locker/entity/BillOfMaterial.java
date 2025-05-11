package com.aits.Safe.Locker.entity;

import jakarta.persistence.*;

@Entity
public class BillOfMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "work_item_id", referencedColumnName = "id")
    private WorkItem workItem;

    @ManyToOne
    @JoinColumn(name = "service_record_id")
    private ServiceRecord serviceRecord;

    public BillOfMaterial() {}

    public BillOfMaterial(Long id, int quantity, WorkItem workItem, ServiceRecord serviceRecord) {
        this.id = id;
        this.quantity = quantity;
        this.workItem = workItem;
        this.serviceRecord = serviceRecord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

    public ServiceRecord getServiceRecord() {
        return serviceRecord;
    }

    public void setServiceRecord(ServiceRecord serviceRecord) {
        this.serviceRecord = serviceRecord;
    }
}
