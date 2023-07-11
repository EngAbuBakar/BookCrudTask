package com.task.crudapplication.Services;

import com.task.crudapplication.DTOs.MembershipDto;
import com.task.crudapplication.DTOs.UserDto;
import com.task.crudapplication.Entity.Membership;
import com.task.crudapplication.Entity.User;
import com.task.crudapplication.Exceptions.MembershipNotFound;
import com.task.crudapplication.Repository.MembershipRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MembershipService {
    @Autowired
    MembershipRepository membershipRepository;
    @Autowired
    ModelMapper modelMapper;
    public Membership addMembership(MembershipDto membershipDto)
    {
        Membership membership=Membership.build(0L,membershipDto.getTierName());
       // if (membershipRepository.findMembershipByTierName(membership.getTierName())==null) {
           return this.membershipRepository.save(membership);
//            return "Membership added successfully";
//        } else {
//            return "Already exist";
//        }
    }

    public List<MembershipDto> getAllMemberships() {
        List<Membership>memberships=this.membershipRepository.findAll();
        List<MembershipDto>membershipDtos=memberships.stream().map(this::membershipToDto).collect(Collectors.toList());

       return membershipDtos;
    }

    public Membership getMembershipById(Long id){
        Membership membership=null;
        try {

            membership=this.membershipRepository.findById(id).get();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  membership;
    }


    public void updatedMembership(Membership membership, Long id) {
        this.membershipRepository.save(membership);
       // return membership;
    }

    public String deleteMembership(Long id) throws MembershipNotFound {
        Optional<Membership> membership=this.membershipRepository.findById(id);
            this.membershipRepository.deleteById(id);
        return "Membership is deleted";
    }




    public Membership dtoToMembership(MembershipDto membershipDto){
        Membership membership=this.modelMapper.map(membershipDto,Membership.class);
        return membership;
    }

    public MembershipDto membershipToDto(Membership membership){
        MembershipDto membershipDto=this.modelMapper.map(membership, MembershipDto.class);
        return membershipDto;
    }



  /*
    public Membership dtoToMembership(Membership membership){
        MembershipDto membershipDto=new MembershipDto();
        membership.setId(membershipDto.getId());
        membership.setTierName(membershipDto.getTierName());
        return membership;
    }

    public MembershipDto membershipToDto(MembershipDto membershipDto){
        Membership membership=new Membership();
        membershipDto.setId(membership.getId());
        membershipDto.setTierName(membership.getTierName());
        return membershipDto;
    }*/


}
