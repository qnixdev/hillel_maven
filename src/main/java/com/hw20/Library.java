package com.hw20;

import com.hw20.dao.AuthorDAO;
import com.hw20.dao.BookDAO;
import com.hw20.description.CLI;
import com.hw20.description.Request;
import com.hw20.model.Author;
import com.hw20.model.Book;

import java.util.*;
import java.util.function.Consumer;

public class Library {
    private final Map<String, Consumer<Scanner>> commands = new HashMap<>();
    private final AuthorDAO authorDAO = new AuthorDAO();
    private final BookDAO bookDAO = new BookDAO();

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
            Optional<Book> existBook = Optional.ofNullable(this.bookDAO.findOneBy("title", bookTitle));

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
                existBook = Optional.ofNullable(this.bookDAO.findOneBy("title", bookTitle));
            }

            System.out.println("Enter author name:");
            Optional<Author> existAuthor = Optional.ofNullable(this.authorDAO.findOneBy("last_name", scanner.nextLine()));
            Author author;

            if (existAuthor.isEmpty()) {
                System.out.println("Author not found! Create new...");
                System.out.println("Enter first name:");
                String firstName = scanner.nextLine();

                System.out.println("Enter last name:");
                String lastName = scanner.nextLine();

                author = Author.builder().firstName(firstName).lastName(lastName).build();
                this.authorDAO.save(author);
            } else {
                author = existAuthor.get();
            }

            this.bookDAO.save(Book.builder().author(author).title(bookTitle).build());
            System.out.println(Request.SAVE.title);
        });
        this.commands.put(CLI.READ.command, scanner -> {
            System.out.println("Enter book title:");
            String bookTitle = scanner.nextLine();
            Optional<Book> existBook = Optional.ofNullable(this.bookDAO.findOneBy("title", bookTitle));

            while (existBook.isEmpty()) {
                System.out.println("Book by title '" + bookTitle + "' not found!");
                System.out.println("Enter book title:");

                bookTitle = scanner.nextLine();
                existBook = Optional.ofNullable(this.bookDAO.findOneBy("title", bookTitle));
            }

            System.out.println(existBook.get());
            System.out.println(Request.NEXT.title);
        });
        this.commands.put(CLI.SHOW.command, scanner -> {
            System.out.println("Enter author name:");
            String authorName = scanner.nextLine();
            Optional<Author> existAuthor = Optional.ofNullable(this.authorDAO.findOneBy("last_name", authorName));

            while (existAuthor.isEmpty()) {
                System.out.println("The author with last name '" + authorName + "' not found!");
                System.out.println("Enter author name:");

                authorName = scanner.nextLine();
                existAuthor = Optional.ofNullable(this.authorDAO.findOneBy("last_name", authorName));
            }

            List<Book> books = this.bookDAO.findByAuthor(existAuthor.get());
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