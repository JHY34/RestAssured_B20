package Z_Practice.Review_Day07;

public class Library_POJO {

    private int book_count;
    private int borrowed_books;
    private int users;

    public Library_POJO () {}

    public Library_POJO (int book_count, int borrowed_books, int users) {
        this.book_count = book_count;
        this.borrowed_books = borrowed_books;
        this.users = users;
    }

    // getBook_count (); <- READ <- NO parameters <- return
    // setBook_count (); <- SET  <- Parameter needed <- void

    public int getBook_count () {
        return book_count;
    }

    public void setBook_count (int book_count) {
        this.book_count = book_count;
    }

    public int getBorrowed_books() {
        return borrowed_books;
    }

    public void setBorrowed_books(int borrowed_books) {
        this.borrowed_books = borrowed_books;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Library_POJO{" +
                "book_count=" + book_count +
                ", borrowed_books=" + borrowed_books +
                ", users=" + users +
                '}';
    }
}
