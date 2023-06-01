package com.baedal.monolithic.domain.store.repository;

import com.baedal.monolithic.domain.store.entity.StoreMenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreOptionRepository extends JpaRepository<StoreMenuOption,Long> {


}
