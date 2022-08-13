package com.example.Irrigation.models;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class IrrigationPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String plan_name;


    private  String duration;
    private  boolean scheduled;
    private  String type;
    @Column(nullable = false)
    private String cron_schedule_expression;
    @ManyToOne
    @JoinColumn(name="plot_id")
    private Plot plot_to_be_irrigated;
//
    @OneToMany(mappedBy = "irrigation_plan")
    private List<IrrigationPlanExecutionHistory> irrigation_plan_execution_history;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Plot getPlot_to_be_irrigated() {
        return plot_to_be_irrigated;
    }

    public void setPlot_to_be_irrigated(Plot plot_to_be_irrigated) {
        this.plot_to_be_irrigated = plot_to_be_irrigated;
    }


    public List<IrrigationPlanExecutionHistory> getIrrigation_plan_execution_history() {
        return irrigation_plan_execution_history;
    }

    public void setIrrigation_plan_execution_history(List<IrrigationPlanExecutionHistory> irrigation_plan_execution_history) {
        this.irrigation_plan_execution_history = irrigation_plan_execution_history;
    }

    @Override
    public String toString() {
        return "IrrigationPlan{" +
                "id=" + id +
                ", plan_name='" + plan_name + '\'' +
                ", duration='" + duration + '\'' +
                ", scheduled=" + scheduled +
                ", type='" + type + '\'' +
                ", cron_schedule_expression='" + cron_schedule_expression + '\'' +
                ", plot_to_be_irrigated=" + plot_to_be_irrigated +
                ", irrigation_plan_execution_history=" + irrigation_plan_execution_history +
                '}';
    }

    public String getCronExpression() {
        return cron_schedule_expression;
    }

    public void setCronExpression(String cron_schedule_expression) {
        this.cron_schedule_expression = cron_schedule_expression;
    }



    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

    public String getName() {
        return plan_name;
    }

    public void setName(String plan_name) {
        this.plan_name = plan_name;
    }
}
