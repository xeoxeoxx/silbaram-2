package com.project.silbaram.dao;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDAO {
    // 회원가입
    void insertMember(MemberVO memberVO);

    // 로그인
    MemberVO selectMemberById(String userId);

    // 자동로그인
    MemberVO selectMemberByUuid(String uuid);
    void updateUuid(Long mid, String uuid);
    // 회원 정보 불러오기
    MemberVO selectMemberByMid(Long mid);

    // 아이디 찾기
    MemberVO selectUserIdByEmail(String email);

    // 비밀번호 재발급
    int updatePasswordByEmailAndUserId (String password, String email, String userId);

    // 중복아이디 체크
    int countMemberByUserId(String userId);
    // 중복닉네임 체크
    int countMemberByUserNickName(String nickName);

    // 회원 정보 수정
    void updateMember(MemberVO memberVO);

    // 비밀번호 수정
    void updatePassword (MemberVO memberVO);

    void deleteMember(MemberVO memberVO);

    //    admin
    List<MemberVO> list(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);

    MemberVO selectMemberByEmail(String email);

}