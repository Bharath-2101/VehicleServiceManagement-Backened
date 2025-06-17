package com.aits.Safe.Locker.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aits.Safe.Locker.entity.WorkItem;
import com.aits.Safe.Locker.entity.WorkItem.Type;

public interface WorkItemRepository extends JpaRepository<WorkItem , Long> {
	
	Optional<WorkItem> findByNameAndType(String name,Type type);

	List<WorkItem> findByType(Type valueOf);

}
