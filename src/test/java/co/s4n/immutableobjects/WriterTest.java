package co.s4n.immutableobjects;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WriterTest {

    @Test
    public void createAWriter() {
        String firstName = "Joshua";
        String lastName = "Backfield";
        Book book1 = new Book("Becoming Functional", 100000d);
        Book book2 = new Book("Book 2", 150000d);
        List<Book> books = Arrays.asList(book1, book2);
        Writer joshua = new Writer(firstName, lastName, books);

        assertEquals(2, joshua.getBooks().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void createAWriterAndModifyBooks() {
        String firstName = "Neal";
        String lastName = "Ford";
        Book book1 = new Book("Functional Thinking", 100000d);
        Book book2 = new Book("The Productive Programmer", 150000d);
        List<Book> books = Arrays.asList(book1, book2);
        Writer neal = new Writer(firstName, lastName, books);

        //Add new Book
        Book book3 = new Book("Book 3", 70000d);
        neal.getBooks().add(book3);

        assertEquals(3, neal.getBooks().size());
    }

    @Test
    public void createAWriterAndModifyBooksInTheImmutableWay() {
        String firstName = "Neal";
        String lastName = "Ford";
        Book book1 = new Book("Functional Thinking", 100000d);
        Book book2 = new Book("The Productive Programmer", 150000d);
        List<Book> books = Arrays.asList(book1, book2);
        Writer neal = new Writer(firstName, lastName, books);

        //Add new Book
        Book book3 = new Book("Book 3", 90000d);
        Writer nealWithMoreBooks = neal.addNewBook(book3);

        assertEquals(2, neal.getBooks().size());
        assertEquals(3, nealWithMoreBooks.getBooks().size());
    }

    @Test
    public void changeBookPriceInTheImmutableWay() throws Exception {
        String firstName = "Joshua";
        String lastName = "Backfield";
        Book book1 = new Book("Becoming Functional", 100000d);
        List<Book> books = Arrays.asList(book1);
        Writer joshua = new Writer(firstName, lastName, books);

        Book book = joshua.getBooks().get(0);
        Writer joshuaWithUpdatedBooks = joshua.changeBookPrice(book, 70000d);

        assertEquals(100000d, joshua.getBooks().get(0).getPrice(), 0d);
        assertEquals(70000d, joshuaWithUpdatedBooks.getBooks().get(0).getPrice(), 0d);
    }

    @Test (expected = Exception.class)
    public void changeBookPriceInTheImmutableWayWithWrongBook() throws Exception {
        String firstName = "Joshua";
        String lastName = "Backfield";
        Book book1 = new Book("Becoming Functional", 100000d);
        List<Book> books = Arrays.asList(book1);
        Writer joshua = new Writer(firstName, lastName, books);

        Book fakeBook = new Book("Fake book", 10000d);
        Writer joshuaWithUpdatedBooks = joshua.changeBookPrice(fakeBook, 70000d);

        assertEquals(70000d, joshuaWithUpdatedBooks.getBooks().get(0).getPrice(), 0d);
    }
}