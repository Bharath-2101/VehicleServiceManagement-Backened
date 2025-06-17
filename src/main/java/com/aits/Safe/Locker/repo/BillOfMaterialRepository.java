package com.aits.Safe.Locker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aits.Safe.Locker.entity.BillOfMaterial;


@Repository
public interface BillOfMaterialRepository extends JpaRepository<BillOfMaterial, Long> {

	List<BillOfMaterial> findByServiceRecordId(long id);

}
