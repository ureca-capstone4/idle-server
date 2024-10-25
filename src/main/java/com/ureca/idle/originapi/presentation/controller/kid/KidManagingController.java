package com.ureca.idle.originapi.presentation.controller.kid;

import com.ureca.idle.originapi.business.kid.KidManagingUseCase;
import com.ureca.idle.originapi.business.kid.dto.*;
import com.ureca.idle.originapi.business.kid.kidsPersonalityDeleteHistory.KidsPersonalityDeleteHistoryUseCase;
import com.ureca.idle.originapi.business.kid.kidsPersonalityDeleteHistory.dto.PutKidsPersonalityToDeleteHistoryResp;
import com.ureca.idle.originapi.presentation.web.auth.LoginUserId;
import com.ureca.idle.originapi.presentation.web.auth.IdAndAuthority;
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
    public ResponseEntity<AddKidResp> addMyKid(@LoginUserId Long userId, @RequestBody AddKidReq req) {
        AddKidResp resp = kidManagingUseCase.addMyKid(userId, req);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("")
    public ResponseEntity<GetKidsProfilesResp> getMyKidsProfiles(@LoginUserId Long userId) {
        GetKidsProfilesResp resp = kidManagingUseCase.getMyKidsProfiles(userId);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{kidId}/detail")
    public ResponseEntity<GetKidsDetailResp> getMyKidsDetail(@PathVariable Long kidId) {
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

