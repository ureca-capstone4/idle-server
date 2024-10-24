package com.ureca.idle.idleapi.idleoriginapi.business.kid.kidsPersonalityDeleteHistory;

import com.ureca.idle.idleapi.idleoriginapi.business.kid.kidsPersonalityDeleteHistory.dto.PutKidsPersonalityToDeleteHistoryResp;
import com.ureca.idle.idleapi.idleoriginapi.implementation.kid.KidManager;
import com.ureca.idle.idleapi.idleoriginapi.implementation.mapper.KidDtoMapper;
import com.ureca.idle.idlejpa.kidspersonality.KidsPersonality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KidsPersonalityDeleteHistoryService implements KidsPersonalityDeleteHistoryUseCase {

    private final KidManager kidManager;
    private final KidDtoMapper kidDtoMapper;

    @Override
    @Transactional
    public PutKidsPersonalityToDeleteHistoryResp putKidsPersonalityToDeleteHistory(Long kidId) {
        // 1. 자녀 성향 삭제 기록 테이블에 현재 성향 Add
        // 2. 자녀 성향 테이블 Random으로 PUT
        // 3. 자녀 엔티티의 FK(Personality ID)와 연결
        // 4. 자녀 성향 변화 기록 테이블에 있는 데이터도 삭제

        KidsPersonality kidsPersonality = kidManager.getKidWithPersonality(kidId).getPersonality();
        kidManager.putKidPersonalityIntoDeleteHistory(kidId, kidsPersonality);
        // 여기에서 기존 꺼 삭제해야하는데..

//        KidsPersonality randomKidsPersonality = kidManager.generateRandomKidsPersonality();
        kidManager.updateRandomKidPersonality(kidId);
        return kidDtoMapper.toDeleteKidsPersonality();
    }
}
