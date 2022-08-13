package com.example.Irrigation.Repos;
import com.example.Irrigation.models.Plot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepo extends JpaRepository <Plot, Long>{
}
