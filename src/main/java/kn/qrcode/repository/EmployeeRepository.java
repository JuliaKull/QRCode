package kn.qrcode.repository;

import kn.qrcode.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {


    @Transactional
    @Modifying
    @Query("update Employee e set e.active = false where e.id =:id")
    void deleteById(@Param("id") Long id);

    Employee findByEmail(String email);

    @Query("select e from Employee e left join e.office left join e.department " +
            " left join e.jobTitle left join e.organization where e.active=:active")
    Page<Employee> findAll(@Param("active")boolean active, Pageable p);




}
