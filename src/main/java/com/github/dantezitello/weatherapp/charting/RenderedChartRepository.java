package com.github.dantezitello.weatherapp.charting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RenderedChartRepository extends JpaRepository<RenderedChartEntity, Integer> {

    Optional<RenderedChartEntity> findByResourceKey(String resourceKey);

}
