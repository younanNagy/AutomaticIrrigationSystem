package com.example.Irrigation.Repos;
import com.example.Irrigation.models.IrrigationPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IrrigationPlanRepo extends JpaRepository<IrrigationPlan,Long> {

    @Query(value = "SELECT * FROM irrigation_plan i WHERE i.plan_name = ?1",nativeQuery = true)
    IrrigationPlan findByName(String plan_name);
}
