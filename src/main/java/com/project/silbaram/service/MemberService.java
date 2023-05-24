package com.project.silbaram.service;


import com.project.silbaram.dto.*;
import com.project.silbaram.vo.MemberVO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    boolean addMember(MemberDTO memberDTO);

    Long login(String userId, String password);

    MemberDTO getByUuid(String uuid);

    void updateUuid(Long mid, String uuid);

    boolean isDuplicatedUserId(String userId);
    boolean isDuplicatedUserNickName(String nickName);

    MemberDTO getMemberByMid(Long mid);

    boolean modifyMemberPwByEmailAndUserId (String password, String email, String userId);

    void modifyMember(MemberModifyDTO memberDTO);

    void modifyMemberPw(MemberPassWordModifyDTO memberDTO);

    void quitMember(MemberDTO memberDTO);

//    void updateUuid(String mid, String uuid);
//
//    MemberDTO getByUuid(String uuid);

    boolean isAdminUser(Long mid);
//    admin

    PageResponseDTO<MemberDTO> list(PageRequestDTO pageRequestDTO);
    MemberDTO getUserIdByEmail(String email);

    MemberDTO getMemberDTOByEmail(String email);
}