package com.task.crudapplication.Services;

import com.task.crudapplication.Entity.Membership;
import com.task.crudapplication.Repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {
    @Autowired
    MembershipRepository membershipRepository;
    public Membership addMembership(Membership membership)
    {
        this.membershipRepository.save(membership);
        return membership;
    }

    public List<Membership> getAllMemberships() {
        return this.membershipRepository.findAll();
    }

    public Membership getMembershipById(Long id){
        return  this.membershipRepository.findById(id).get();
    }
}
