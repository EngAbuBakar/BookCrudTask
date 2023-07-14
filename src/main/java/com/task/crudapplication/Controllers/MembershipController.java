package com.task.crudapplication.Controllers;

import com.task.crudapplication.DTOs.MembershipDto;
import com.task.crudapplication.Entity.Book;
import com.task.crudapplication.Entity.Membership;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Exceptions.MembershipNotFound;
import com.task.crudapplication.Services.MembershipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/memberships")
public class MembershipController {
    @Autowired
    MembershipService membershipService;

    @PostMapping("/")
    public ResponseEntity<Membership> addMembership(@RequestBody @Valid MembershipDto membershipDto) {
       List<MembershipDto>membershipDtoList=this.membershipService.getAllMemberships();
       if (membershipDtoList.size()<=0){
           return new ResponseEntity<>(this.membershipService.addMembership(membershipDto), HttpStatus.CREATED);
       }
       else {

           return new ResponseEntity<>(HttpStatus.FOUND);
       }
    }

    @GetMapping("/")
    public ResponseEntity<List<MembershipDto>> getAllMemberships(MembershipDto membershipDto) {
        List<MembershipDto> memberships = membershipService.getAllMemberships();
        if (memberships.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(memberships));
    }

    @GetMapping(value = "/{membershipId}")
    public ResponseEntity<Membership> getMembership(@PathVariable("membershipId") Long id) throws MembershipNotFound {
        Membership membership = membershipService.getMembershipById(id);
        if (membership == null) {
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            throw new MembershipNotFound("Membership not found with the id:" + id);
        }
        return ResponseEntity.of(Optional.of(membership));
    }

    @PutMapping(value = "/{membershipId}")
    public Membership updateMembership(@RequestBody MembershipDto membershipDto, @PathVariable("membershipId") Long id) throws MembershipNotFound {
        Membership memberships = membershipService.getMembershipById(id);
        if (memberships == null) {
            throw new MembershipNotFound("Membership not found with the id:" + id);
        }
        membershipDto.setId(id);
        this.membershipService.updatedMembership(membershipDto, id);
        return memberships;
    }

    @DeleteMapping("/{membershipId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteMembership(@PathVariable("membershipId") Long id) throws MembershipNotFound {
        Membership membership = membershipService.getMembershipById(id);
        if (membership == null) {
            throw new MembershipNotFound("Membership not found with the id:" + id);
        } else
            this.membershipService.deleteMembership(id);
        return "deleted successfully";
    }
}
