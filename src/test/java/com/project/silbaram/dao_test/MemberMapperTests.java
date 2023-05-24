package com.project.silbaram.dao_test;

import com.project.silbaram.dao.MemberDAO;
import com.project.silbaram.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class MemberMapperTests {

    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void insertMemberTest() {
        MemberVO memberVO = MemberVO.builder()
                .userId("ididi12")
                .password("ppassword123@")
                .nickName("닉aa2")
                .name("John Doe")
                .birthDate(LocalDate.of(2000,01,01))
                .gender("여")
                .phoneNumber("01010000000")
                .email("@example1.com")
//                .zipcode("12345")
//                .address("123 Main St, Anytown USA")
//                .socialLogin(false)
//                .isAdmin(false)
                .build();
        log.info(memberVO.getEmail());
        memberDAO.insertMember(memberVO);
    }

    @Test
    public void getMemberByMidTest() {
        MemberVO memberVO = memberDAO.selectMemberByMid(1L);
        log.info(memberVO);
    }

    @Test
    public void countMemberByUserIdTest() {
        int count = memberDAO.countMemberByUserId("aaaa");
        log.info("count: " +count);
    }

    @Test
    public void updateMemberTest() {
        MemberVO memberVO = MemberVO.builder()
                .mid(1L)
                .userId("ssss123")
                .nickName("newNickName")
                .password("password123")
                .name("John Doe")
                .birthDate(LocalDate.of(2000,01,01))
                .gender("여")
                .phoneNumber("01012345678")
                .email("@example1.coma")
                .build();
        log.info(memberVO);
        memberDAO.updateMember(memberVO);
    }


}
