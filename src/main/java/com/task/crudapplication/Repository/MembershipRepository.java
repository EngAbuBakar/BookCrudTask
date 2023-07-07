package com.task.crudapplication.Repository;

import com.task.crudapplication.Entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership,Long> {
    public Optional<Membership> findById(Long id );
    Membership findMembershipBytierName(String tierName);
}
