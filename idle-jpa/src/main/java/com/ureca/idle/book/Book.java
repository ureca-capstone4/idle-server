package com.ureca.idle.book;

import com.ureca.idle.booksdetail.BooksDetail;
import com.ureca.idle.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "books_detail_id")
    private BooksDetail booksDetail;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String story;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private int recommendedAge;
}
