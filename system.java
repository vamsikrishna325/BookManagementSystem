import java.util.*;
class Book {
    private String title;
    private String author;
    private boolean issued;
    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.issued = false;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isIssued() {
        return issued;
    }
    public void issue() {
        issued = true;
    }
    public void returnBook() {
        issued = false;
    }
    public String toString() {
        return title + " by " + author + (issued ? " (Issued)" : " (Available)");
    }
}

class User {
    private String name;
    private ArrayList<Book> borrowed;
    User(String name) {
        this.name = name;
        this.borrowed = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void borrowBook(Book b) {
        borrowed.add(b);
    }
    public void returnBook(Book b) {
        borrowed.remove(b);
    }
    public ArrayList<Book> getBorrowedBooks() {
        return borrowed;
    }
}

class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }
    public void addBook(Book b) {
        books.add(b);
    }
    public void addUser(User u) {
        users.add(u);
    }
    public void issueBook(String title, String userName) {
        Book found = null;
        User u = null;
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title) && !b.isIssued()) {
                found = b;
                break;
            }
        }
        for (User usr : users) {
            if (usr.getName().equalsIgnoreCase(userName)) {
                u = usr;
                break;
            }
        }
        if (found != null && u != null) {
            found.issue();
            u.borrowBook(found);
            System.out.println("Book issued successfully");
        } else {
            System.out.println("Book cannot be issued");
        }
    }
    public void returnBook(String title, String userName) {
        User u = null;
        for (User usr : users) {
            if (usr.getName().equalsIgnoreCase(userName)) {
                u = usr;
                break;
            }
        }
        if (u != null) {
            Book toReturn = null;
            for (Book b : u.getBorrowedBooks()) {
                if (b.getTitle().equalsIgnoreCase(title)) {
                    toReturn = b;
                    break;
                }
            }
            if (toReturn != null) {
                toReturn.returnBook();
                u.returnBook(toReturn);
                System.out.println("Book returned successfully");
                return;
            }
        }
        System.out.println("Return failed");
    }
    public void showBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }
    public void showUsers() {
        for (User u : users) {
            System.out.println(u.getName() + " has borrowed " + u.getBorrowedBooks().size() + " books");
        }
    }
}

public class system{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        lib.addBook(new Book("Java Basics","James Gosling"));
        lib.addBook(new Book("Effective Java","Joshua Bloch"));
        lib.addBook(new Book("Clean Code","Robert Martin"));
        lib.addUser(new User("Alice"));
        lib.addUser(new User("Bob"));
        int ch;
        do {
            System.out.println("\n1. Show Books\n2. Show Users\n3. Issue Book\n4. Return Book\n5. Exit");
            ch = sc.nextInt();
            sc.nextLine();
            if (ch == 1) {
                lib.showBooks();
            } else if (ch == 2) {
                lib.showUsers();
            } else if (ch == 3) {
                System.out.print("Enter book title: ");
                String t = sc.nextLine();
                System.out.print("Enter user name: ");
                String u = sc.nextLine();
                lib.issueBook(t,u);
            } else if (ch == 4) {
                System.out.print("Enter book title: ");
                String t = sc.nextLine();
                System.out.print("Enter user name: ");
                String u = sc.nextLine();
                lib.returnBook(t,u);
            }
        } while (ch != 5);
        sc.close();
    }
}
