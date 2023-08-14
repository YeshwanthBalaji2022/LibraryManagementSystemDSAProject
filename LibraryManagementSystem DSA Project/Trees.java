public class Trees {



}

class Node<type>{
    type data;
    Node left;
    Node right;

    Node(type value){
        this.data = value;
        this.right=null;
        this.left = null;
    }


}



class Books {
    int bookid;
    String authorName;
    String bookname;

    Books(int bookid , String bookname, String authorName){
        this.bookname = bookname;
        this.bookid = bookid;
        this.authorName=authorName;
    }
}

class Users{
    int userid;
    String username;

    Users(int userid,String username){
        this.userid=userid;
        this.username=username;
    }
}


class LibManSystem{

    private Node<Books> bookRoot;
    private Node<Users> userRoot;



    LibManSystem(){
        bookRoot = null;
        bookRoot = null;

    }

    public void regUser(int userid, String username) {
        Users newUser = new Users(userid, username);
        userRoot = insertNode(userRoot, newUser);
        System.out.println("User registered successfully.");
    }

    public void loginUser(int id) {
        Node<Users> userNode = findNode(userRoot, id);
        if (userNode == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Welcome, " + userNode.data.username + "!");
    }
    private Node<Books> insertNode(Node<Books> root, Books book) {
        if (root == null) {
            return new Node<>(book);
        }

        if (book.bookid < root.data.bookid) {
            root.left = insertNode(root.left, book);
        } else if (book.bookid > root.data.bookid) {
            root.right = insertNode(root.right, book);
        }

        return root;
    }

    private Node<Users> insertNode(Node<Users> root, Users user) {
        if (root == null) {
            return new Node<>(user);
        }

        if (user.userid < root.data.userid) {
            root.left = insertNode(root.left, user);
        } else if (user.userid > root.data.userid) {
            root.right = insertNode(root.right, user);
        }

        return root;
    }

    private <type> Node<type> findNode(Node<type> root, int id) {
        if (root == null || ((Users)root.data).userid == id) {
            return root;
        }

        if (id < ((Users)root.data).userid) {
            return findNode(root.left, id);
        } else {
            return findNode(root.right, id);
        }
    }



}