public class Main {

  // 시민 운영 도서관 시민들이 책을 기부하고 대출 및 반납이 가능함

  public static void main(String[] args) {
    Library library = Library.getLibrary();

    Book book1 = new Book("돈키호테");
    Book book2 = new Book("어린 왕자");


    library.addBook(book1);
    library.addBook(book2);

    library.borrowBook(book2.getTitle());
    library.returnBook(book1.getTitle());
  }
}