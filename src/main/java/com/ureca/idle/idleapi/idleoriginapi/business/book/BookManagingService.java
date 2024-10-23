package com.ureca.idle.idleapi.idleoriginapi.business.book;

import com.ureca.idle.idleapi.idleoriginapi.business.book.dto.*;
import com.ureca.idle.idleapi.idleoriginapi.implementation.book.BookManager;
import com.ureca.idle.idleapi.idleoriginapi.implementation.mapper.BookDtoMapper;
import com.ureca.idle.idlejpa.book.Book;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookManagingService implements BookManagingUseCase {

    private final BookManager bookManager;
    private final BookDtoMapper bookDtoMapper;

    @Override
    @Transactional
    public AddBookResp addBook(AddBookReq req) {
        // TODO BooksDetail newBooksDetail = add~;
        Book newBook = bookManager.addBook(req.title(), req.story(), req.summary(), req.author(), req.publisher(), req.recommendedAge());
        return bookDtoMapper.toAddBookResp(newBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long bookId) {
        bookManager.checkExistsBook(bookId);
        bookManager.deleteBook(bookId);
    }

    @Override
    @Transactional
    public void updateBook(Long bookId, UpdateBookReq req) {
        bookManager.checkExistsBook(bookId);
        bookManager.updateBook(bookId, req);
    }

    @Override
    @Transactional(readOnly = true)
    public GetBookDetailResp getBookDetail(Long bookId, Long kidId) {
        Book book = bookManager.getBook(bookId);
        String preference = bookManager.getBookPreference(bookId, kidId);
        return bookDtoMapper.toSelectBookDetailResp(book,preference);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetBookProfileResp> getRecommendedBooks(Long kidId) {
        List<Book> recommendedBooks = bookManager.getRecommendedBooks(kidId);
        if(recommendedBooks.isEmpty()){
            recommendedBooks = bookManager.getRandomBooks(10);
        }
        return bookDtoMapper.toGetBookProfileResp(recommendedBooks);
    }
}
