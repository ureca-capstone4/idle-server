package com.ureca.idle.idleapi.idleoriginapi.implementation.mapper;

import com.ureca.idle.idleapi.idleoriginapi.business.kid.dto.*;
import com.ureca.idle.idleapi.idleoriginapi.business.kid.dto.UpdateKidPersonalityResp;
import com.ureca.idle.idleapi.idleoriginapi.business.kid.kidsPersonalityDeleteHistory.dto.PutKidsPersonalityToDeleteHistoryResp;
import com.ureca.idle.idlejpa.kid.Kid;
import com.ureca.idle.idlejpa.kidspersonality.KidsPersonality;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KidDtoMapper {

    public AddKidResp toAddKidResp(Kid newKid) {
        return new AddKidResp("성공적으로 " + newKid.getName() + " 이(가) 등록되었습니다.");
    }

    public GetKidsProfilesResp toGetMyKidsProfilesResp(List<Kid> kids) {
        return new GetKidsProfilesResp(
                kids.stream()
                        .map(this::toGetMyKidsProfileResp)
                        .toList()
        );
    }

    public GetKidsProfileResp toGetMyKidsProfileResp(Kid kid) {
        return new GetKidsProfileResp(kid.getId(), kid.getName(), kid.getProfileImageUrl());
    }

    public GetKidsDetailResp toGetKidsDetailResp(Kid kid) {
        KidsPersonality personality = kid.getPersonality();
        return new GetKidsDetailResp(
                new GetKidsProfileResp(kid.getId(), kid.getName(), kid.getProfileImageUrl()),
                new GetKidsPersonalityResp(personality.getId(), personality.getEi(), personality.getSn(), personality.getTf(), personality.getJp(), personality.getMbti())
        );
    }

    public UpdateKidPersonalityResp toUpdateKidPersonality() {
        return new UpdateKidPersonalityResp("성공적으로 성향이 등록되었습니다.");

    }

    public PutKidsPersonalityToDeleteHistoryResp toDeleteKidsPersonality() {
        return new PutKidsPersonalityToDeleteHistoryResp("성공적으로 성향이 삭제되었습니다.");
    }
}
