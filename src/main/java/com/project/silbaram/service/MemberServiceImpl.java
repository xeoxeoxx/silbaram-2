package com.project.silbaram.service;


import com.project.silbaram.dao.MemberDAO;
import com.project.silbaram.dto.*;
import com.project.silbaram.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{


    private final MemberDAO memberDAO;
    private final ModelMapper modelMapper;


    @Override
    public boolean addMember(MemberDTO memberDTO) {
        log.info(modelMapper);
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberDAO.insertMember(memberVO);
        log.info(memberVO);
        return true;
    }

    @Override
    public boolean isDuplicatedUserId(String userId) {
        int count = memberDAO.countMemberByUserId(userId);
        return count > 0; // true == 중복, false면 사용가능한 아이디
    }

    @Override
    public boolean isDuplicatedUserNickName(String nickName) {
        int count = memberDAO.countMemberByUserNickName(nickName);
        return count > 0; // true == 중복, false면 사용가능한 닉네임
    }

    @Override
    public MemberDTO getMemberByMid(Long mid) {
        MemberVO memberVO = memberDAO.selectMemberByMid(mid);
        if (memberVO == null) {
            return null;
        }
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        return memberDTO;
    }

    @Override
    public boolean modifyMemberPwByEmailAndUserId(String password, String email, String userId) {
        int updates = memberDAO.updatePasswordByEmailAndUserId(password, email, userId);

        return updates > 0;
    }



    @Override
    public void modifyMember(MemberModifyDTO memberDTO) {
        log.info(modelMapper.toString());
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberDAO.updateMember(memberVO);
        log.info(memberVO);
    }

    @Override
    public void modifyMemberPw(MemberPassWordModifyDTO memberDTO) {
        log.info(modelMapper);
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberDAO.updatePassword(memberVO);
        log.info(memberVO);
    }

    @Override
    public MemberDTO getUserIdByEmail(String email) {
        MemberVO memberVO = memberDAO.selectUserIdByEmail(email);
        if (memberVO == null) {
            return null;
        }
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);

        return memberDTO;
    }


    @Override
    public MemberDTO getMemberDTOByEmail(String email) {
        MemberVO memberVO = memberDAO.selectMemberByEmail(email);
        if (memberVO == null) {
            return null;
        }
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        return memberDTO;
    }

    @Override
    public Long login(String userId, String password) {
        MemberVO memberVO = memberDAO.selectMemberById(userId);
        if (memberVO == null) {
            return null;
        }
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        if(memberDTO.getPassword().equals(password)) {
            return memberDTO.getMid();
        }
        return null;
    }

    @Override
    public MemberDTO getByUuid(String uuid) {
        MemberVO memberVO = memberDAO.selectMemberByUuid(uuid);
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        return memberDTO;
    }

    @Override
    public void updateUuid(Long mid, String uuid) {
        // 자동 로그인을 사용하는 경우 임의의 문자열을 저장
        memberDAO.updateUuid(mid,uuid);
    }

    @Override
    public void quitMember(MemberDTO memberDTO) {
        log.info(modelMapper.toString());
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberDAO.deleteMember(memberVO);
        log.info(memberVO);
    }

    //    admin


    @Override
    public boolean isAdminUser(Long mid) {
        MemberVO member = memberDAO.selectMemberByMid(mid);

        if (member != null && member.isAdmin()) {
            return true;
        }

        return false;
    }

//    admin

    @Override
    public PageResponseDTO<MemberDTO> list(PageRequestDTO pageRequestDTO) {
        log.info("service - list");
        // !! 조건 검색 쿼리로 변경
        // List<BoardVO> voList = boardDAO.selectAll();
        List<MemberVO> voList = memberDAO.list(pageRequestDTO);
        log.info(voList);
        List<MemberDTO> dtoList = new ArrayList<>();
        for (MemberVO memberVO : voList) {
            dtoList.add(modelMapper.map(memberVO, MemberDTO.class));
        }
        int total = memberDAO.getCount(pageRequestDTO);

        PageResponseDTO<MemberDTO> pageResponseDTO = PageResponseDTO.<MemberDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;

    }



}