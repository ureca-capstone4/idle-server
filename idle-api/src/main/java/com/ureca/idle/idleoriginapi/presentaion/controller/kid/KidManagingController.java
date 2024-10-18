package com.ureca.idle.idleoriginapi.presentaion.controller.kid;

import com.ureca.idle.idleoriginapi.business.kid.KidManagingUseCase;
import com.ureca.idle.idleoriginapi.business.kid.dto.AddKidReq;
import com.ureca.idle.idleoriginapi.business.kid.dto.AddKidResp;
import com.ureca.idle.idleoriginapi.business.kid.dto.GetKidsDetailResp;
import com.ureca.idle.idleoriginapi.business.kid.dto.GetKidsProfilesResp;
import com.ureca.idle.idleoriginapi.presentaion.web.auth.LoginUser;
import com.ureca.idle.idleoriginapi.presentaion.web.auth.IdAndAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/kids")
@RequiredArgsConstructor
public class KidManagingController {

    private final KidManagingUseCase kidManagingUseCase;

    @PostMapping("")
    public ResponseEntity<AddKidResp> addMyKid(@LoginUser IdAndAuthority loginUser, @RequestBody AddKidReq req) {
        AddKidResp resp = kidManagingUseCase.addMyKid(loginUser.id(), req);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("")
    public ResponseEntity<GetKidsProfilesResp> getMyKidsProfiles(@LoginUser IdAndAuthority loginUser) {
        GetKidsProfilesResp resp = kidManagingUseCase.getMyKidsProfiles(loginUser.id());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{kidId}/detail")
    public ResponseEntity<GetKidsDetailResp> getMyKidsDetail(/*TODO @LoginUser 이외에 인증인가 검증 필터를 만들 것*/@LoginUser IdAndAuthority loginUser, @PathVariable Long kidId) {
        GetKidsDetailResp resp = kidManagingUseCase.getKidsDetail(kidId);
        return ResponseEntity.ok(resp);
    }
}

