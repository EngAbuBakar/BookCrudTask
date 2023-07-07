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
    public String addMembership(Membership membership)
    {
       // if (membershipRepository.findMembershipBytierName(membership.getTierName())==null) {
            this.membershipRepository.save(membership);
            return "Membership added successfully";
//        } else {
//            return "Already exist";
//        }
    }

    public List<Membership> getAllMemberships() {
        return this.membershipRepository.findAll();
    }

    public Membership getMembershipById(Long id){
        return  this.membershipRepository.findById(id).get();
    }


    public Membership updatedMembership(Membership membership, Long id) {
        this.membershipRepository.save(membership);
        return membership;
    }

    public String deleteMembership(Long id) {
        this.membershipRepository.deleteById(id);
        return "Membership deleted:";
    }
}
