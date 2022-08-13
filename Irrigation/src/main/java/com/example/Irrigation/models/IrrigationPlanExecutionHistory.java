package com.example.Irrigation.models;



import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class IrrigationPlanExecutionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String irrigation_status;


    @ManyToOne
    @JoinColumn(name="irrigation_plan_id")
    private IrrigationPlan irrigation_plan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIrrigation_status() {
        return irrigation_status;
    }

    public void setIrrigation_status(String irrigation_status) {
        this.irrigation_status = irrigation_status;
    }

    public IrrigationPlan getIrrigation_plan() {
        return irrigation_plan;
    }

    public void setIrrigation_plan(IrrigationPlan irrigation_plan) {
        this.irrigation_plan = irrigation_plan;
    }

    @Override
    public String toString() {
        return "IrrigationPlanExecutionHistory{" +
                "id=" + id +
                ", irrigation_status='" + irrigation_status + '\'' +
                ", irrigation_plan=" + irrigation_plan +
                '}';
    }
}
