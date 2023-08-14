import java.util.Scanner;

class Book {
    int id;
    String title;
    String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + "| BookTitle: " + title + "| Author: "+ author;
    }
}

class User {
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User ID: " + id + "| Name: " + name;
    }
}

class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class LibraryManagementSystem {
    private TreeNode<Book> bookRoot;
    private TreeNode<User> userRoot;

    public LibraryManagementSystem() {
        bookRoot = null;
        userRoot = null;
    }
    private TreeNode<Book> insertNode(TreeNode<Book> root, Book book) {
        if (root == null) {
            return new TreeNode<>(book);
        }

        if (book.id < root.data.id) {
            root.left = insertNode(root.left, book);
        } else if (book.id > root.data.id) {
            root.right = insertNode(root.right, book);
        }

        return root;
    }

    private TreeNode<User> insertNode(TreeNode<User> root, User user) {
        if (root == null) {
            return new TreeNode<>(user);
        }

        if (user.id < root.data.id) {
            root.left = insertNode(root.left, user);
        } else if (user.id > root.data.id) {
            root.right = insertNode(root.right, user);
        }

        return root;
    }

    private TreeNode<Book> deleteNode(TreeNode<Book> root, int id) {
        if (root == null) {
            return null;
        }

        if (id < root.data.id) {
            root.left = deleteNode(root.left, id);
        } else if (id > root.data.id) {
            root.right = deleteNode(root.right, id);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode<Book> successor = findMin(root.right);
            root.data = successor.data;
            root.right = deleteNode(root.right, successor.data.id);
        }

        return root;
    }

    private TreeNode<Book> findMin(TreeNode<Book> node) {
        if (node.left != null) {
            return findMin(node.left);
        }

        return node;
    }

    private TreeNode<User> findUserNode(TreeNode<User> root, int id) {
        if (root == null || ((User)root.data).id == id) {
            return root;
        }

        if (id < ((User)root.data).id) {
            return findUserNode(root.left, id);
        } else {
            return findUserNode(root.right, id);
        }
    }

    private TreeNode<Book> findBookNode(TreeNode<Book> root, int id) {
        if (root == null || ((Book)root.data).id == id) {
            return root;
        }

        if (id < ((Book)root.data).id) {
            return findBookNode(root.left, id);
        } else {
            return findBookNode(root.right, id);
        }
    }

    private <T> void printInorder(TreeNode<T> root) {
        if (root != null) {
            printInorder(root.left);
            System.out.println(root.data);
            printInorder(root.right);
        }
    }
    public void registerUser(int id, String name) {
        User newUser = new User(id, name);
        userRoot = insertNode(userRoot, newUser);
        System.out.println("User registered successfully.");
    }

    public boolean loginUser(int id) {
        TreeNode<User> userNode = findUserNode(userRoot, id);
        if (userNode == null) {
            System.out.println("User not found.");
            return false;
        }
        else {
            System.out.println("Welcome, " + userNode.data.name + "!");
            return true;
        }

    }

    public void addBook(int id, String title, String author) {
        Book newBook = new Book(id, title, author);
        bookRoot = insertNode(bookRoot, newBook);
        System.out.println("Book added successfully.");
    }

    public void deleteBook(int id) {
        bookRoot = deleteNode(bookRoot, id);
        System.out.println("Book deleted successfully.");
    }

    public void issueBook(int bookId, int userId) {
        TreeNode<Book> bookNode = findBookNode(bookRoot, bookId);
        TreeNode<User> userNode = findUserNode(userRoot, userId);

        if (bookNode == null || userNode == null) {
            System.out.println("Book or user not found.");
            return;
        }

        System.out.println("Book '" + bookNode.data.title + "' issued to user '" + userNode.data.name + "'.");
    }

    public void renewBook(int bookId, int userId) {
        TreeNode<Book> bookNode = findBookNode(bookRoot, bookId);
        TreeNode<User> userNode = findUserNode(userRoot, userId);

        if (bookNode == null || userNode == null) {
            System.out.println("Book or user not found.");
            return;
        }

        System.out.println("Book '" + bookNode.data.title + "' renewed for user '" + userNode.data.name + "'.");
    }

    public void printUsers() {
        System.out.println("User Database:");
        printInorder(userRoot);
    }

    public void printBooks() {
        if(bookRoot == null){
            System.out.println("No Books are in them library. Request admin to add the books...");

        }
        else {
            System.out.println("Books:");
            printInorder(bookRoot);
        }
    }


}

public class Main {
    public static void main(String[] args) {

        int userCount = 0,bookCount = 0;

        try {
            LibraryManagementSystem library = new LibraryManagementSystem();
            Scanner scanner = new Scanner(System.in);
            int choice;
            int adminoruser;

            do {

                System.out.println("--------- Library Management System ---------");
                System.out.println("Users count = " + userCount + ", Book Count = "+bookCount);
                System.out.println("1. Admin");
                System.out.println("2. User");
                System.out.println("3. Exit");
                System.out.println("Enter your choice");
                adminoruser = scanner.nextInt();

                switch (adminoruser) {
                    case 1:
                        do {
                            System.out.println("--------- Library Management System ---------");
                            System.out.println("Users count = " + userCount + ", Book Count = "+bookCount);
                            System.out.println("NOTE:  PLEASE ENTER THE VALUES OF USERS AND BOOKS GREATER THAN THEIR COUNTS WHILE REGISTERING AND DELETING");
                            System.out.println("1. Add Book");
                            System.out.println("2. Delete Book");
                            System.out.println("3. Issue Book");
                            System.out.println("4. Renew Book");
                            System.out.println("5. Print User Database");
                            System.out.println("6. Print Book DataBase");
                            System.out.println("7. Exit");
                            System.out.print("Enter your choice: ");
                            choice = scanner.nextInt();


                            switch (choice) {

                                case 1:
                                    System.out.println("⚠️Make sure you enter the book id only in integers and book title and author names in string, do not enter any integers ⚠️ \n  ⚠️Do not enter the previously entered data while this program is run.⚠️");
                                    System.out.print("Enter Book ID(in numbers): ");
                                    int bookId = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter Book Title(in words): ");
                                    String bookTitle = scanner.nextLine();
                                    System.out.print("Enter Book Author(in words): ");
                                    String bookAuthor = scanner.nextLine();
                                    if (bookId>bookCount){
                                    library.addBook(bookId, bookTitle, bookAuthor);
                                    bookCount = bookCount + 1;
                                    } else if (bookId<0) {
                                        System.out.println("ID cannot be negative");
                                    } else {
                                        System.out.println("Book ID already exists");
                                    }
                                    break;
                                case 2:
                                    System.out.print("Enter Book ID(in numbers): ");
                                    int deleteId = scanner.nextInt();
                                    library.deleteBook(deleteId);
                                    bookCount = bookCount - 1;
                                    break;
                                case 3:
                                    System.out.print("Enter Book ID(in numbers): ");
                                    int issueBookId = scanner.nextInt();
                                    System.out.print("Enter User ID(in numbers): ");
                                    int issueUserId = scanner.nextInt();
                                    library.issueBook(issueBookId, issueUserId);
                                    break;
                                case 4:
                                    System.out.print("Enter Book ID(in numbers): ");
                                    int renewBookId = scanner.nextInt();
                                    System.out.print("Enter User ID(in numbers): ");
                                    int renewUserId = scanner.nextInt();
                                    library.renewBook(renewBookId, renewUserId);
                                    break;
                                case 5:
                                    library.printUsers();
                                    break;
                                case 6:
                                    library.printBooks();
                                    break;
                                case 7:
                                    System.out.println("Exiting Library Management System. Goodbye!");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        } while (choice != 7);
                        break;

                    case 2:
                        do {
                            System.out.println("--------- Library Management System ---------");
                            System.out.println("Users count = " + userCount + ", Book Count = "+bookCount);
                            System.out.println("NOTE:  PLEASE ENTER THE VALUES OF USERS AND BOOKS GREATER THAN THEIR COUNTS WHILE REGISTERING AND DELETING");
                            System.out.println("1. Register as User");
                            System.out.println("2. Login as User");
                            System.out.println("3. Exit");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1:
                                    System.out.print("Enter User ID(in numbers): ");
                                    int userId = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter User Name(in words): ");
                                    String userName = scanner.nextLine();
                                    if (userId>userCount){
                                    library.registerUser(userId, userName);
                                    userCount = userCount + 1;
                                    break;
                                    } else if (userId<0) {
                                        System.out.println("ID cannot be negative");
                                    } else {
                                        System.out.println("User ID already taken");
                                    }

                                case 2:
                                    System.out.print("Enter User ID(in numbers): ");
                                    int loginId = scanner.nextInt();
                                    boolean x = library.loginUser(loginId);
                                    if (x) {


                                        do {
                                            System.out.println("1. Request List of Books");
                                            System.out.println("2. Exit");
                                            choice = scanner.nextInt();

                                            switch (choice) {
                                                case 1:
                                                    library.printBooks();
                                                    break;
                                                case 2:
                                                    System.out.println("Exiting Library Management System. Goodbye!");
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice. Please try again.");


                                            }
                                        } while (choice != 2);

                                    } else {
                                        System.out.println("Unable to login please try again");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Exiting Library Management System. Goodbye!");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        } while (choice != 3);
                        break;


                    case 3:
                        System.out.println("Exiting Library Management System. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice");

                }


            } while (adminoruser != 3);

        }

     catch (Exception e) {
        System.out.println("🛑Enter appropriate values only🛑 Please try again");
    } catch (Error e) {
        System.out.println("Something went wrong due to the input, please try again");
    }
    }
}

