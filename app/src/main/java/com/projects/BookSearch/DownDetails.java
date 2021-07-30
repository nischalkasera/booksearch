package com.projects.BookSearch;

public class DownDetails {
    private String url;
    private String title;
    private String author;
    private String publisher;
    private String year;


    public DownDetails(String title, String author, String publisher, String year, String url) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuth() {
        return author;
    }

    public void setAuth(String author) {
        this.author = author;
    }

    public String getPub() {
        return publisher;
    }

    public void setPub(String publisher) {
        this.publisher = publisher;
    }

    public  String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUrl() { return url;  }

    public void setURL(String string) { this.url = url; }
}