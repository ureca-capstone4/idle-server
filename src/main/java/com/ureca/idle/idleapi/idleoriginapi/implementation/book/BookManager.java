package com.ureca.idle.idleapi.idleoriginapi.implementation.book;

import com.ureca.idle.idleaiclient.business.dto.AddBookMbtiReq;
import com.ureca.idle.idleaiclient.business.dto.AddBookMbtiResp;
import com.ureca.idle.idleaiclient.implementation.AiClientManager;
import com.ureca.idle.idleapi.idleoriginapi.business.book.dto.AddBookReq;
import com.ureca.idle.idleapi.idleoriginapi.business.book.dto.UpdateBookReq;
import com.ureca.idle.idleapi.idleoriginapi.implementation.kid.KidManager;
import com.ureca.idle.idleapi.idleoriginapi.implementation.util.MBTI;
import com.ureca.idle.idleapi.idleoriginapi.implementation.util.MBTIUtil;
import com.ureca.idle.idleapi.idleoriginapi.persistence.book.BookPreferenceRepository;
import com.ureca.idle.idleapi.idleoriginapi.persistence.book.BookRepository;
import com.ureca.idle.idleapi.idleoriginapi.persistence.book.BooksCharacteristicRepository;
import com.ureca.idle.idlejpa.book.Book;
import com.ureca.idle.idlejpa.bookscharacteristic.BooksCharacteristic;
import com.ureca.idle.idlejpa.kidspersonality.KidsPersonality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookManager {

    private final BookRepository bookRepository;
    private final BookPreferenceRepository bookPreferenceRepository;
    private final BooksCharacteristicRepository booksCharacteristicRepository;
    private final KidManager kidManager;
    private final MBTIUtil mbtiUtil;
    private final AiClientManager aiClientManager;

    public Book addBook(AddBookReq req, BooksCharacteristic newBooksCharacteristic) {
        Book newBook = Book.builder()
                .booksCharacteristic(newBooksCharacteristic)
                .title(req.title())
                .story(req.story())
                .summary(req.summary())
                .author(req.author())
                .publisher(req.publisher())
                .recommendedAge(req.recommendedAge())
                .bookImageUrl(req.bookImageUrl())
                .build();
        return bookRepository.save(newBook);
    }

    public BooksCharacteristic generateBooksCharacteristicByAI(AddBookReq req) {
        AddBookMbtiReq MbtiReq = new AddBookMbtiReq(req.title(), req.summary(), req.story());
        AddBookMbtiResp resp = aiClientManager.createBookMbti(MbtiReq);
        String mbti = mbtiUtil.getMBTIByElement(resp.ei(), resp.sn(), resp.tf(), resp.jp());
        BooksCharacteristic newBooksCharacteristic = BooksCharacteristic.builder()
                .ei(resp.ei())
                .sn(resp.sn())
                .tf(resp.tf())
                .jp(resp.jp())
                .mbti(mbti)
                .build();
        return booksCharacteristicRepository.save(newBooksCharacteristic);
    }

    public void updateBook(Long bookId, UpdateBookReq req) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException(BookExceptionType.BOOK_NOT_FOUND_EXCEPTION));
        book.updateBook(req.title(), req.story(), req.summary(), req.author(), req.publisher(), req.recommendedAge(), req.bookImageUrl());
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public void checkExistsBook(Long bookId) {
        if(!bookRepository.existsById(bookId)) {
            throw new BookException(BookExceptionType.BOOK_NOT_FOUND_EXCEPTION);
        }
    }

    public Book getBook(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book
                .orElseThrow(() -> new BookException(BookExceptionType.BOOK_NOT_FOUND_EXCEPTION));
    }

    public String getBookPreference(Long bookId, Long kidId) {
        return bookPreferenceRepository.findBookPreferenceWithBookAndKid(bookId, kidId)
                .map(bookPreference -> bookPreference.getHobulho().getValue())
                .orElse("none");

    }

    public List<Book> getRecommendedBooks(Long kidId){
        KidsPersonality kidsPersonality = kidManager.getKidWithPersonality(kidId).getPersonality();
        return bookRepository.getRecommendedBooksByKidPersonality(
                kidsPersonality.getEi(),
                kidsPersonality.getSn(),
                kidsPersonality.getTf(),
                kidsPersonality.getJp());
    }

    public List<Book> getRandomBooks(int quantity){
        return bookRepository.getRandomBooks(quantity);
    }

    public BooksCharacteristic generateRandomBooksCharacteristic() {
        MBTI randomMBTI = mbtiUtil.generateRandomMBTI();
        BooksCharacteristic randomBooksCharacteristic = BooksCharacteristic.builder()
                .ei(randomMBTI.ei())
                .sn(randomMBTI.sn())
                .tf(randomMBTI.tf())
                .jp(randomMBTI.jp())
                .mbti(randomMBTI.mbti())
                .build();
        return booksCharacteristicRepository.save(randomBooksCharacteristic);
    }
}