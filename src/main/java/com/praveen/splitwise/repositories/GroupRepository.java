package com.praveen.splitwise.repositories;

import com.praveen.splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long>{
    Group save(Group group);
    Optional<Group> findById(Long id);


}
