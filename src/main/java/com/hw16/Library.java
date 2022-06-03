package com.hw16;

import com.hw16.description.CLI;
import com.hw16.description.Request;
import com.hw16.model.Author;
import com.hw16.model.Book;
import com.hw16.repository.AuthorRepository;
import com.hw16.repository.BookRepository;

import java.util.*;
import java.util.function.Consumer;

public class Library {
    private final Map<String, Consumer<Scanner>> commands = new HashMap<>();
    private final AuthorRepository authorRepository = new AuthorRepository();
    private final BookRepository bookRepository = new BookRepository();

    {
        this.init();
    }

    public void run() {
        System.out.println(Request.WELCOME.title);

        String command;
        var systemScanner = new Scanner(System.in);

        do {
            command = systemScanner.nextLine().toLowerCase();
            this.commands
                .getOrDefault(command, scanner -> System.out.println(Request.NOT_FOUND.title))
                .accept(systemScanner)
            ;
        } while (!command.equals("exit"));
    }

    private void init() {
        this.commands.put(CLI.ADD.command, scanner -> {
            System.out.println("Enter book title:");
            String bookTitle = scanner.nextLine();
            Optional<Book> existBook = this.bookRepository.findOneByTitle(bookTitle);

            while (existBook.isPresent()) {
                System.out.println("The book with title '" + existBook.get().getTitle() + "' already exist!");
                System.out.println("Show this book (yes/no)?");
                String showBook = scanner.nextLine();

                if (Objects.equals(showBook, "y") || Objects.equals(showBook, "yes")) {
                    System.out.println(existBook.get());
                    System.out.println(Request.NEXT.title);
                } else {
                    System.out.println("Enter book title:");
                }

                bookTitle = scanner.nextLine();
                existBook = this.bookRepository.findOneByTitle(bookTitle);
            }

            System.out.println("Enter author name:");
            Optional<Author> existAuthor = this.authorRepository.findOneByName(scanner.nextLine());
            Author author;

            if (existAuthor.isEmpty()) {
                System.out.println("Author not found! Create new...");
                System.out.println("Enter first name:");
                String firstName = scanner.nextLine();

                System.out.println("Enter last name:");
                String lastName = scanner.nextLine();

                author = new Author(firstName, lastName);
                this.authorRepository.save(author);
            } else {
                author = existAuthor.get();
            }

            this.bookRepository.save(new Book(bookTitle, author));
            System.out.println(Request.SAVE.title);
        });
        this.commands.put(CLI.READ.command, scanner -> {
            System.out.println("Enter book title:");
            String bookTitle = scanner.nextLine();
            Optional<Book> existBook = this.bookRepository.findOneByTitle(bookTitle);

            while (existBook.isEmpty()) {
                System.out.println("Book by title '" + bookTitle + "' not found!");
                System.out.println("Enter book title:");

                bookTitle = scanner.nextLine();
                existBook = this.bookRepository.findOneByTitle(bookTitle);
            }

            System.out.println(existBook.get());
            System.out.println(Request.NEXT.title);
        });
        this.commands.put(CLI.SHOW.command, scanner -> {
            System.out.println("Enter author name:");
            String authorName = scanner.nextLine();
            Optional<Author> existAuthor = this.authorRepository.findOneByName(authorName);

            while (existAuthor.isEmpty()) {
                System.out.println("The author with last name '" + authorName + "' not found!");
                System.out.println("Enter author name:");

                authorName = scanner.nextLine();
                existAuthor = this.authorRepository.findOneByName(authorName);
            }

            List<Book> books = this.bookRepository.findByAuthor(existAuthor.get());
            System.out.println(books);
            System.out.println(Request.NEXT.title);
        });
        this.commands.put(CLI.EXIT.command, scanner -> {
            System.out.println(Request.BYE.title);
        });
        this.commands.put(CLI.HELP.command, scanner -> {
            Arrays.stream(CLI.values()).forEach(System.out::println);
        });
    }
}