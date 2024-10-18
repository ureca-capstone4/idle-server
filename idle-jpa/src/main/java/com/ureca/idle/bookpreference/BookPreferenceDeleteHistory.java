package com.ureca.idle.bookpreference;

import com.ureca.idle.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;



@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BookPreferenceDeleteHistory extends BaseEntity { // TODO 성향 삭제 시 기타 데이터로 포함될 것과 실제 선호 취소 기록과 네이밍 혼동, 추후 처리

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long booksId;

    @Column(nullable = false)
    private Long kidsId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Hobulho hobulho;
}
