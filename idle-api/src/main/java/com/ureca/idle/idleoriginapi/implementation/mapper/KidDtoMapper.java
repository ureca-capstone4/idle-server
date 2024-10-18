package com.ureca.idle.idleoriginapi.implementation.mapper;

import com.ureca.idle.idleoriginapi.business.kid.dto.*;
import com.ureca.idle.kid.Kid;
import com.ureca.idle.kidspersonality.KidsPersonality;
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
}
