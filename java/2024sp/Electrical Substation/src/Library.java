import java.util.ArrayList;
import java.util.List;

public class Library {
  private static Library library = new Library();
  private List<Book> books;

  private Library() {
    books = new ArrayList<>();
  }

  public static Library getLibrary() {
    return library;
  }

  // 책 추가 메소드
  public void addBook(Book book) {
    books.add(book);
    System.out.println(book.getTitle()+"를 대출하였습니다.");
  }

  // 책 대출 메소드
  public synchronized void borrowBook(String title) {
    for (Book book : books) {
      if (book.getTitle().equals(title) && !book.isBorrowed()) {
        book.borrowBook();
        System.out.println(book.getTitle()+"를 대출하였습니다.");
        return;
      }
      else if(book.getTitle().equals(title) && book.isBorrowed()){
        System.out.println(book.getTitle()+"은 이미 대출되었습니다.");
        return;
      }
    }
    System.out.println("책에 대한 정보가 없습니다.");
  }

  // 책 반납 메소드
  public synchronized void returnBook(String title){
    for (Book book : books) {
      if (book.getTitle().equals(title) && book.isBorrowed()) {
        book.returnBook();
        System.out.println(book.getTitle() + "를 반납하였습니다.");
        return;
      }
      else if(book.getTitle().equals(title) && !book.isBorrowed()){
        System.out.println(book.getTitle()+"는 대출한 적이 없습니다.");
        return;
      }
    }
    System.out.println("책에 대한 정보가 없습니다.");
  }
}
