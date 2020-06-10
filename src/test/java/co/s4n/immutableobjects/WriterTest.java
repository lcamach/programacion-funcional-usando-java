package co.s4n.immutableobjects;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WriterTest {

    @Test
    public void createAWriter() {
        String firstName = "Pepito";
        String lastName = "Perez";
        Book book1 = new Book("Las aventuras de Pepito", 100000d);
        Book book2 = new Book("¿cómo sobrevivir en un mundo sin pepes", 150000d);
        List<Book> books = new ArrayList<>(Arrays.asList(book1, book2));
        Writer pepito = new Writer(firstName, lastName, books);

        assertEquals(2, pepito.getBooks().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void createAWriterAndModifyBooks() {
        String firstName = "Pepito";
        String lastName = "Perez";
        Book book1 = new Book("Las aventuras de Pepito", 100000d);
        Book book2 = new Book("¿cómo sobrevivir en un mundo sin pepes?", 150000d);
        List<Book> books = new ArrayList<>(Arrays.asList(book1, book2));
        Writer pepito = new Writer(firstName, lastName, books);

        //Add new Book
        Book book3 = new Book("Las tristezas de Pepe", 90000d);
        pepito.getBooks().add(book3);

        assertEquals(3, pepito.getBooks().size());
    }

    @Test
    public void createAWriterAndModifyBooksInTheImmutableWay() {
        String firstName = "Pepito";
        String lastName = "Perez";
        Book book1 = new Book("Las aventuras de Pepito", 100000d);
        Book book2 = new Book("¿cómo sobrevivir en un mundo sin Pepes?", 150000d);
        List<Book> books = new ArrayList<>(Arrays.asList(book1, book2));
        Writer pepito = new Writer(firstName, lastName, books);

        //Add new Book
        Book book3 = new Book("Las tristezas de Pepe", 90000d);
        Writer pepitoWithMoreBooks = pepito.addNewBook(book3);

        assertEquals(2, pepito.getBooks().size());
        assertEquals(3, pepitoWithMoreBooks.getBooks().size());
    }

    @Test (expected = AssertionError.class)
    public void changeBookPrice() {
        String firstName = "Pepito";
        String lastName = "Perez";
        Book book1 = new Book("Las aventuras de Pepito", 100000d);
        List<Book> books = new ArrayList<>(Arrays.asList(book1));
        Writer pepito = new Writer(firstName, lastName, books);

        pepito.getBooks().get(0).changePrice(90000d);

        assertEquals(90000d, pepito.getBooks().get(0).getPrice(), 0d);
    }

    @Test
    public void changeBookPriceInTheImmutableWay() throws Exception {
        String firstName = "Pepito";
        String lastName = "Perez";
        Book book1 = new Book("Las aventuras de Pepito", 100000d);
        List<Book> books = new ArrayList<>(Arrays.asList(book1));
        Writer pepito = new Writer(firstName, lastName, books);

        Book book = pepito.getBooks().get(0);
        Writer pepitoWithUpdatedBooks = pepito.changeBookPrice(book, 70000d);

        assertEquals(100000d, pepito.getBooks().get(0).getPrice(), 0d);
        assertEquals(70000d, pepitoWithUpdatedBooks.getBooks().get(0).getPrice(), 0d);
    }

    @Test (expected = Exception.class)
    public void changeBookPriceInTheImmutableWayWithWrongBook() throws Exception {
        String firstName = "Pepito";
        String lastName = "Perez";
        Book book1 = new Book("Las aventuras de Pepito", 100000d);
        List<Book> books = new ArrayList<>(Arrays.asList(book1));
        Writer pepito = new Writer(firstName, lastName, books);

        Book book = new Book("Libro pirata", 10000d);
        Writer pepitoWithUpdatedBooks = pepito.changeBookPrice(book, 70000d);
    }

}