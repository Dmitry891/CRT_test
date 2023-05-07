package api;

public class Request extends RequestWithoutName {

    public Object name;

    public Request(Object name) {
        this.name = name;
    }

    public Request(Object isElectronicBook, Object year, Object author, Object name) {
        super(isElectronicBook, year, author);
        this.name = name;
    }

}
