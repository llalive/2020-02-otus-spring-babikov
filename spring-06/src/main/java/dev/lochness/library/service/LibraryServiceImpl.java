package dev.lochness.library.service;

import dev.lochness.library.domain.Author;
import dev.lochness.library.domain.Book;
import dev.lochness.library.domain.Comment;
import dev.lochness.library.domain.Genre;
import dev.lochness.library.io.Printer;
import dev.lochness.library.repository.AuthorRepository;
import dev.lochness.library.repository.BookRepository;
import dev.lochness.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final Printer printer;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public LibraryServiceImpl(Printer printer, BookRepository bookRepository,
                              GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.printer = printer;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void listBooks() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            printer.printBookInfo(book, false);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void printBookInfo(long bookId) {
        printer.printBookInfo(bookRepository.findBookById(bookId), true);
    }

    @Override
    @Transactional(readOnly = true)
    public void listAuthors() {
        List<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            printer.printAuthor(author, false);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void listGenres() {
        List<Genre> genres = genreRepository.findAll();
        for (Genre genre : genres) {
            printer.printGenre(genre, false);
        }
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        return bookRepository.saveBook(book);
    }

    @Override
    @Transactional
    public void deleteBook(long id) {
        bookRepository.deleteBookById(id);
    }

    @Override
    @Transactional
    public void setBookGenres(long bookId, List<Long> genresId) {
        Book book = bookRepository.findBookById(bookId);
        List<Genre> genres = new ArrayList<>();
        for (Long genreId : genresId) {
            genres.add(genreRepository.findGenreById(genreId));
        }
        book.setGenres(genres);
        bookRepository.saveBook(book);
    }

    @Override
    @Transactional
    public void setBookAuthors(long bookId, List<Long> authorsId) {
        Book book = bookRepository.findBookById(bookId);
        List<Author> authors = new ArrayList<>();
        for (Long authorId : authorsId) {
            Optional<Author> author = authorRepository.findAuthorById(authorId);
            author.ifPresent(authors::add);
        }
        book.setAuthors(authors);
        bookRepository.saveBook(book);
    }

    @Override
    @Transactional(readOnly = true)
    public void printBooksByGenre(long genreId) {
        printer.printGenre(genreRepository.findGenreById(genreId), true);
    }

    @Override
    @Transactional(readOnly = true)
    public void printBooksByAuthor(long authorId) {
        Optional<Author> author = authorRepository.findAuthorById(authorId);
        author.ifPresent(value -> printer.printAuthor(value, true));
    }

    @Override
    @Transactional
    public Genre addGenre(Genre genre) {
        return genreRepository.saveGenre(genre);
    }

    @Override
    @Transactional
    public Author addAuthor(Author author) {
        return authorRepository.saveAuthor(author);
    }

    @Override
    @Transactional
    public void deleteGenre(long genreId) {
        genreRepository.deleteGenreById(genreId);
    }

    @Override
    @Transactional
    public void deleteAuthor(long authorId) {
        authorRepository.deleteAuthorById(authorId);
    }

    @Override
    @Transactional
    public void updateBook(long bookId, String title, String isbn) {
        Book book = bookRepository.findBookById(bookId);
        book.setTitle(title);
        book.setIsbn(isbn);
        bookRepository.saveBook(book);
    }

    @Override
    @Transactional
    public void addComment(long bookId, Comment comment) {
        Book book = bookRepository.findBookById(bookId);
        book.getComments().add(comment);
        bookRepository.saveBook(book);
    }

}
