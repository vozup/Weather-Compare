package com.vozup.weathercompare.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SitesRepository extends JpaRepository<SitesEntity, Long> {
    boolean existsBySite(String site);
}
