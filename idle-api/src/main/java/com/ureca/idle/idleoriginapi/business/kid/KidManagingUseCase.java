package com.ureca.idle.idleoriginapi.business.kid;

import com.ureca.idle.idleoriginapi.business.kid.dto.AddKidReq;
import com.ureca.idle.idleoriginapi.business.kid.dto.AddKidResp;
import com.ureca.idle.idleoriginapi.business.kid.dto.GetKidsDetailResp;
import com.ureca.idle.idleoriginapi.business.kid.dto.GetKidsProfilesResp;

public interface KidManagingUseCase {
    AddKidResp addMyKid(Long userId, AddKidReq req);
    GetKidsProfilesResp getMyKidsProfiles(Long userId);
    GetKidsDetailResp getKidsDetail(Long kidId);
}
