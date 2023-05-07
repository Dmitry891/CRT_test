package api;

import lombok.Getter;


public class Response{
	private Book book;

	public Response(Book book) {
		this.book = book;
	}

	public Response() {
	}

	public Book getBook() {
		return book;
	}
}
