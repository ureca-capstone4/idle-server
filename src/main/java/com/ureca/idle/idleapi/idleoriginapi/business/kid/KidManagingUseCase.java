package com.ureca.idle.idleapi.idleoriginapi.business.kid;

import com.ureca.idle.idleapi.idleoriginapi.business.kid.dto.*;
import com.ureca.idle.idleapi.idleoriginapi.business.kid.dto.UpdateKidPersonalityReq;
import com.ureca.idle.idleapi.idleoriginapi.business.kid.dto.UpdateKidPersonalityResp;

public interface KidManagingUseCase {
    AddKidResp addMyKid(Long userId, AddKidReq req);
    GetKidsProfilesResp getMyKidsProfiles(Long userId);
    GetKidsDetailResp getKidsDetail(Long kidId);
    UpdateKidPersonalityResp updateKidsPersonality(Long kidId, UpdateKidPersonalityReq req);

}
