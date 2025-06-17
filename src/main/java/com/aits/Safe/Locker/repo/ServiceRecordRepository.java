package com.aits.Safe.Locker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aits.Safe.Locker.entity.ServiceRecord;
import com.aits.Safe.Locker.entity.ServiceRecord.Status;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord , Long>{

	List<ServiceRecord> findByStatus(Status status);
	List<ServiceRecord> findByUserId(long id);
	List<ServiceRecord> findByUserIdAndStatus(long id, Status pending);

}
