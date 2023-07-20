package com.task.crudapplication.Services;

import com.task.crudapplication.DTOs.MembershipDto;
import com.task.crudapplication.DTOs.UserDto;
import com.task.crudapplication.Entity.Membership;
import com.task.crudapplication.Repository.MembershipRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@ExtendWith(MockitoExtension.class)
class MembershipServiceTest {
   @InjectMocks
    private MembershipService membershipService;
    @Mock
    private MembershipRepository membershipRepository;

    Membership expectedMembership1;
    Membership mockMembership;


    @BeforeEach
    void setUp() {
        membershipService = new MembershipService(membershipRepository);
        expectedMembership1 = new Membership();
        expectedMembership1.setId(1L);
        expectedMembership1.setTierName("gold");
    }

    @Test
    public void testGetMembershipById() {
        // Sample input
        Long id = 1L;

        // Create a mock Membership object
         mockMembership = new Membership();
        mockMembership.setId(id);
        // You can set other fields for the mock object as needed

        // Mock the behavior of membershipRepository.findById(id)
        when(membershipRepository.findById(id)).thenReturn(Optional.of(mockMembership));

        // Call the method under test
        Membership result = membershipService.getMembershipById(id);

        // Verify that the repository method was called with the correct id
        verify(membershipRepository).findById(id);

        // Perform assertions on the result
        assertNotNull(result);
        assertEquals(id, result.getId());

    }


    @Test
    public void getAllMemberships() {

        expectedMembership1 = new Membership();
        expectedMembership1.setId(1L);
        expectedMembership1.setTierName("gold");

        List<Membership> memberships = new ArrayList<>();
         memberships.add(Membership.build(3L,"Bronze"));

         //when(membershipRepository.findAll()).thenReturn(memberships);

        List<MembershipDto> result = membershipService.getAllMemberships();


        // assertEquals(1,result.size());

        MembershipDto membershipDto1 = result.get(1);

        assertEquals(1L, membershipDto1.getId());
        assertEquals("gold", membershipDto1.getTierName());
//
        //when(membershipRepository.findAll()).thenReturn(Stream.of(new Membership(id,tierName),new Membership(1L,"gold")).collect(Collectors.toList()));
        membershipService.getAllMemberships();
        verify(membershipRepository,times(1)).findAll();
    }

    @Test
    public void testAddMembership() {
        MembershipDto membershipDto = new MembershipDto();
        membershipDto.setId(1L);
        membershipDto.setTierName("gold");

         when(membershipRepository.save(any(Membership.class))).thenReturn(expectedMembership1);
        assertEquals(1L, expectedMembership1.getId());
        assertEquals("gold", expectedMembership1.getTierName());

    }


    @Test
    public void testUpdatedMembership() {
        MembershipDto membershipDto = new MembershipDto();
        membershipDto.setId(1L);
        membershipDto.setTierName("Gold2");

        Long id = 1L;
        when(membershipRepository.findById(id)).thenReturn(Optional.of(new Membership()));
        membershipService.updatedMembership(membershipDto, id);
        verify(membershipRepository, times(1)).findById(id);

        verify(membershipRepository, times(1)).save(any(Membership.class));
    }



}