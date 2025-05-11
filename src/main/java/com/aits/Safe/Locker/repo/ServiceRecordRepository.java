package com.aits.Safe.Locker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aits.Safe.Locker.entity.ServiceRecord;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord , Long>{

}
