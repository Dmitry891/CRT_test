package api;

public class RequestWithoutName {
    public Object isElectronicBook = false;
    public Object year = 0;
    public Object author = "";

    public RequestWithoutName(Object isElectronicBook, Object year, Object author) {
        this.isElectronicBook = isElectronicBook;
        this.year = year;
        this.author = author;
    }

    public void setIsElectronicBook(Object isElectronicBook) {
        this.isElectronicBook = isElectronicBook;
    }

    public void setYear(Object year) {
        this.year = year;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public Object getElectronicBook() {
        return isElectronicBook;
    }

    public Object getYear() {
        return year;
    }

    public Object getAuthor() {
        return author;
    }

    public RequestWithoutName() {
    }

    public RequestWithoutName(Object isElectronicBook) {
        this.isElectronicBook = isElectronicBook;
    }

    public RequestWithoutName(Object isElectronicBook, Object year) {
        this.isElectronicBook = isElectronicBook;
        this.year = year;
    }


}
