package com.ruiya.stream;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/9 11:23
 * @history:
 */
public class Book {
    public static void main(String[] args) {
        Book book = new Book().setBookId("b.001").setTitle("庆余年").setCover("http://localhost/qyn.jpg");
        System.out.println(book);
    }

    private String bookId;
    private String title;
    private String cover;

    public String getBookId() {
        return bookId;
    }

    public Book setBookId(String bookId) {
        this.bookId = bookId;
        // 返回当前对象
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        // 返回当前对象
        return this;
    }

    public String getCover() {
        return cover;
    }

    public Book setCover(String cover) {
        this.cover = cover;
        // 返回当前对象
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
