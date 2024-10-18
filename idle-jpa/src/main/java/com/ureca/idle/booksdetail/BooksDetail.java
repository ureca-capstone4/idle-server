package com.ureca.idle.booksdetail;

import com.ureca.idle.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BooksDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int ei;

    @Column(nullable = false)
    private int sn;

    @Column(nullable = false)
    private int tf;

    @Column(nullable = false)
    private int jp;

    @Column(nullable = false)
    private String mbti;
}
