package com.navanee.demo.repository;

import com.navanee.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    

    @Query("SELECT e FROM Employee e WHERE e.designation = :designation")
    List<Employee> findByDesignation(@Param("designation") String designation);


    @Query("SELECT e FROM Employee e WHERE e.employeeName LIKE %:keyword%")
    List<Employee> searchByName(@Param("keyword") String keyword);

    @Query("SELECT e FROM Employee e")
    Page<Employee> findAllEmployees(Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.task.id = :taskId")
    List<Employee> findByTask(@Param("taskId") Long taskId);
}
