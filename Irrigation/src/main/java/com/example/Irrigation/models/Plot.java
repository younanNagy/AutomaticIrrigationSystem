package com.example.Irrigation.models;


import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String plot_name;
    private String area;
    private String iot_device_status;

//    @UniqueConstraint()
    @Column(unique = true)
    private String iot_device_id;
    @OneToMany(mappedBy = "plot_to_be_irrigated")
    private List<IrrigationPlan> irrigation_plans;

    public void updatePlotData(Plot new_plot) {

        this.plot_name = new_plot.plot_name;
        this.area = new_plot.area;
        this.iot_device_status =new_plot.iot_device_status;
        this.iot_device_id = new_plot.iot_device_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public List<IrrigationPlan> getIrrigation_plans() {
        return irrigation_plans;
    }

    public void setIrrigation_plans(List<IrrigationPlan> irrigation_plans) {
        this.irrigation_plans = irrigation_plans;
    }



    public String getIot_device_status() {
        return iot_device_status;
    }

    public void setIot_device_status(String iot_device_status) {
        this.iot_device_status = iot_device_status;
    }

    public String getIot_device_id() {
        return iot_device_id;
    }

    public void setIot_device_id(String iot_device_id) {
        this.iot_device_id = iot_device_id;
    }

    public String getPlot_name() {
        return plot_name;
    }

    public void setPlot_name(String plot_name) {
        this.plot_name = plot_name;
    }
}

