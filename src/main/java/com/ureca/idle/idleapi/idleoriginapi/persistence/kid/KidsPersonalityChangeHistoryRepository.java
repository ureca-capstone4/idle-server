package com.ureca.idle.idleapi.idleoriginapi.persistence.kid;

import com.ureca.idle.idlejpa.kidspersonality.KidsPersonalityChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KidsPersonalityChangeHistoryRepository extends JpaRepository<KidsPersonalityChangeHistory, Long> {
    // TODO: 자녀 성향이 Default로 생성된 경우, 히스토리에 저장여부에 대한 로직 완성 시 개발 예정
}
