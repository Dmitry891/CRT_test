package api;

public class RequestWithoutIsElectronicBook {

    public Object year = 0;
    public Object name = "";
    public Object author = "";

    public RequestWithoutIsElectronicBook(Object year, Object name, Object author) {
        this.year = year;
        this.name = name;
        this.author = author;
    }

    public RequestWithoutIsElectronicBook() {
    }

    public Object getYear() {
        return year;
    }

    public Object getName() {
        return name;
    }

    public Object getAuthor() {
        return author;
    }
}
