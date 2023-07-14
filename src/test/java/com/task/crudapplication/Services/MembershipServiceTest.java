package com.task.crudapplication.Services;

import com.task.crudapplication.DTOs.MembershipDto;
import com.task.crudapplication.Entity.Membership;
import com.task.crudapplication.Repository.MembershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MembershipServiceTest {
    @Mock
    private MembershipRepository membershipRepository;
    private MembershipService membershipService;
    private Membership membership;
    private MembershipDto membershipDto;

    @BeforeEach
    void setUp() {
        this.membershipService=new MembershipService(this.membershipRepository);
        MembershipDto membershipDto=new MembershipDto(02L,"gold");

    }

    @Test
    void getAllMemberships() {
        membershipService.getAllMemberships();
        verify(this.membershipRepository).findAll();
    }

    @Test
    void testCreateMembership(){
        mock(Membership.class);
        mock(MembershipRepository.class);
        when(membershipRepository.save(membership)).thenReturn(membership);
       // assertThat(membership,membershipService.addMembership(membershipDto)).isEqualTo("Created");



    }
}