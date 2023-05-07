package api;

public class RequestWithoutAuthor{

    private Object isElectronicBook = false;
    private Object year = 0;
    private Object name = "";

    public RequestWithoutAuthor(Object isElectronicBook, Object year, Object name) {
        this.isElectronicBook = isElectronicBook;
        this.year = year;
        this.name = name;
    }

    public RequestWithoutAuthor() {
    }

    public Object getIsElectronicBook() {
        return isElectronicBook;
    }

    public Object getYear() {
        return year;
    }

    public Object getName() {
        return name;
    }
}
