package com.task.crudapplication.Controllers;

import com.task.crudapplication.Entity.Membership;
import com.task.crudapplication.Services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MembershipController {
    @Autowired
    MembershipService membershipService;
    @PostMapping("/addMembership")
    public Membership addMemberShip(@RequestBody Membership membership)
    {
        this.membershipService.addMembership(membership);
        return membership;
    }
    @GetMapping("/getAllMembership")
    public List<Membership> getAllMemberships(){
       List<Membership> membershipList=(List<Membership>) this.membershipService.getAllMemberships();
       return membershipList;
    }
    @GetMapping (value = "getMembershipById/{id}")
    private Membership getUser(@PathVariable("id") Long id){
        return membershipService.getMembershipById(id);
    }

}
