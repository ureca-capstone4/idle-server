package com.ureca.idle.idlejpa.book;

import com.ureca.idle.idlejpa.bookscharacteristic.BooksCharacteristic;
import com.ureca.idle.idlejpa.config.BaseEntity;
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
    @JoinColumn(name = "books_characteristic_id", nullable = false)
    private BooksCharacteristic booksCharacteristic;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String story;

    @Lob
    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private int recommendedAge;
    // TODO book image url 추가

    public void updateBook(String title, String story, String summary, String author, String publisher, int recommendedAge) {
        this.title = title;
        this.story = story;
        this.summary = summary;
        this.author = author;
        this.publisher = publisher;
        this.recommendedAge = recommendedAge;
    }
}
