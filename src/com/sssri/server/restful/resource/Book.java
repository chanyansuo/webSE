package com.sssri.server.restful.resource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * RESTful resource. Provider for webservice test.
 * 
 * @author huangjw(huangjw@primeton.com)
 *
 */
@XmlRootElement(name="book")
public class Book {

	private String name;
	
	private String content;
	
	public Book() {
	}
	
	public Book(String name, String content){
		this.name = name;
		this.content = content;
	}

	/**
	 * @return the {@link #name}
	 */
	@XmlElement
	public String getName() {
		return name;
	}

	/**
	 * @param name the {@link #name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the {@link #content}
	 */
	@XmlElement
	public String getContent() {
		return content;
	}

	/**
	 * @param content the {@link #content} to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return this.name+":"+this.content;
	}
}
