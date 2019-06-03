package com.sssri.server.restful;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

import com.sssri.server.restful.resource.Book;

/**
 * @author huangjw(huangjw@primeton.com)
 *
 */
@Path("/library")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class BookRestService4WS {

	public static List<Book> books = new ArrayList<Book>();
	static {
		books.add(new Book("在人间", "速度jfk十多个"));
		books.add(new Book("悲惨世界", "雨果描述的悲惨世界"));
	}
	
	@GET
	@Path("/books")
	public List<Book> listBooks(){
		return books;
	}
	
	@GET
	@Path("/allbooks")
	public Book[] getAllBooks(){
		return books.toArray(new Book[books.size()]);
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GET
	@Path("/books/{id}")
	public Book getBook(@PathParam("id")String id){
		if(Integer.parseInt(id)>=2){
			return null;
		} else {
			return books.get(Integer.parseInt(id));
		}
	}
	
	/**
	 * @param book
	 */
	@PUT
	@Path("/book/{name}")
	public void updateBook(@PathParam("name") PathSegment newBook){
		Iterator<Book> it = books.iterator();
		String name = String.valueOf(newBook.getMatrixParameters().get("name"));
		String content = String.valueOf(newBook.getMatrixParameters().get("content"));
		while(it.hasNext()){
			Book mybook = it.next();
			if(name.equals(mybook.getName())){
				mybook.setContent(content);
				break;
			}
		}
	}
	
	@POST
	@Path("/books/{name}/{content}")
	public void addBook(@PathParam("name")String name, @PathParam("content")String content){
		books.add(new Book(name, content));
	}
	
	@DELETE
	@Path("/books/{name}")
	public void delBook(@PathParam("name")String name){
		Iterator<Book> it = books.iterator();
		while(it.hasNext()){
			Book mybook = it.next();
			if(mybook.getName().equals(name)){
				books.remove(mybook);
				break;
			}
		}
	}
}
