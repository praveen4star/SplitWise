package com.praveen.splitwise.repositories;

import com.praveen.splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long>{
    Group save(Group group);
}
