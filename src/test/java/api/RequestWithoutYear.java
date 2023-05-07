package api;

public class RequestWithoutYear {
    public Object isElectronicBook = false;
    public Object author = "";
    public Object name = "";

    public RequestWithoutYear(Object isElectronicBook, Object author, Object name) {
        this.isElectronicBook = isElectronicBook;
        this.author = author;
        this.name = name;
    }

    public Object getIsElectronicBook() {
        return isElectronicBook;
    }

    public Object getAuthor() {
        return author;
    }

    public Object getName() {
        return name;
    }
}
