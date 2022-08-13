package com.example.Irrigation.Repos;
import com.example.Irrigation.models.IrrigationPlanExecutionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IrrigationPlanExecutionHistoryRepo extends JpaRepository<IrrigationPlanExecutionHistory,Long> {
}
