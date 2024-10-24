package com.ureca.idle.idleapi.idleoriginapi.presentation.controller.kid;

import com.ureca.idle.idleapi.idleoriginapi.business.kid.KidManagingUseCase;
import com.ureca.idle.idleapi.idleoriginapi.business.kid.dto.*;
import com.ureca.idle.idleapi.idleoriginapi.business.kid.kidsPersonalityDeleteHistory.KidsPersonalityDeleteHistoryUseCase;
import com.ureca.idle.idleapi.idleoriginapi.business.kid.kidsPersonalityDeleteHistory.dto.PutKidsPersonalityToDeleteHistoryResp;
import com.ureca.idle.idleapi.idleoriginapi.presentation.web.auth.LoginUser;
import com.ureca.idle.idleapi.idleoriginapi.presentation.web.auth.IdAndAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/kids")
@RequiredArgsConstructor
public class KidManagingController {

    private final KidManagingUseCase kidManagingUseCase;
    private final KidsPersonalityDeleteHistoryUseCase kidsPersonalityDeleteHistoryUseCase;

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


    @PostMapping("/{kidId}/personalities")
    public ResponseEntity<UpdateKidPersonalityResp> updateKidsPersonalities(@PathVariable Long kidId, @RequestBody UpdateKidPersonalityReq req) {
        // @LoginUser IdAndAuthority loginUser,
        UpdateKidPersonalityResp resp = kidManagingUseCase.updateKidsPersonality(kidId, req);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{kidId}/personalities")
//    public ResponseEntity<DeleteKidsPersonalityResp> deleteKidsPersonalities(@LoginUser IdAndAuthority loginUser, @PathVariable Long kidId, @RequestBody UpdateKidPersonalityReq req) {
    public ResponseEntity<PutKidsPersonalityToDeleteHistoryResp> deleteKidsPersonalities(@PathVariable Long kidId) {
        PutKidsPersonalityToDeleteHistoryResp resp = kidsPersonalityDeleteHistoryUseCase.putKidsPersonalityToDeleteHistory(kidId);
        return ResponseEntity.ok(resp);
    }



}

