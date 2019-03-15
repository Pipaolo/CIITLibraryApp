package edu.ciit.library_app.Models;

import java.util.ArrayList;

public class VerticalModel
{
    String bookGenre;
    ArrayList<HorizontalModel> arrayList;

    public VerticalModel(String genre, ArrayList<HorizontalModel> bookList)
    {
        bookGenre = genre;
        arrayList = bookList;
    }

    public ArrayList<HorizontalModel> getArrayList() {
        return arrayList;
    }

    public String getBookGenre() {
        return bookGenre;
    }

}
