package edu.ciit.library_app;

public class books {

    private String bookTitle;
    private String bookGenre;

    public books(String title, String genre)
    {
        bookTitle = title;
        bookGenre = genre;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookGenre(){
        return bookGenre;
    }


}
