package com.dao;

import java.util.ArrayList;
import java.util.Map;

import com.dto.Book;

public class ArrayListBookDataAccessDaoImpl implements BookDataAccessDao {
	// To save Book object to ArrayList
	ArrayList<Book> arrBook = new ArrayList<Book>();


	@Override
	public boolean saveBook(Book b) {
		// TODO Auto-generated method stub

		if(arrBook.add(b) == true){
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Book> selectAll() {
		// TODO Auto-generated method stub
		//return null;
		return arrBook;
	}

	@Override
	public Book selectByNo(int no) {
		// TODO Auto-generated method stub

		for(Book tmp : arrBook){
			if(tmp.getNo() == no){
				return tmp;
			}
		}
		// 사실 compare을 사용해서 해야 하는 것 같음..
		return null;
	}

	@Override
	public boolean updateBook(int bookNo, Map<Integer, String> updateContent) {
		// TODO Auto-generated method stub
		Book updatingBook = selectByNo(bookNo);
		String changeString;
		if(updatingBook != null){
			for(int i = 1; i <= 3; i++){
				changeString = updateContent.get(i);
				switch(i){
				case 1:
					// autoboxing
					if(changeString != null){
						arrBook.get(arrBook.indexOf(updatingBook)).setName(changeString);
					}
					break;
				case 2:
					if(changeString != null){
						arrBook.get(arrBook.indexOf(updatingBook)).setAuthor(changeString);
					}
					break;
				case 3:
					if(changeString != null){
						arrBook.get(arrBook.indexOf(updatingBook)).setPublisher(changeString);
					}
					break;
				default:
					break;
				}
			}
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteBook(int no) {
		// TODO Auto-generated method stub
		Book removeBook = selectByNo(no);
		if(removeBook != null){
			return arrBook.remove(removeBook);
		}
		return false;
	}

	@Override
	public ArrayList<Book> searchBookByName(String subName) {
		// TODO Auto-generated method stub
		ArrayList<Book> searchedBook = new ArrayList<Book>();

		for(Book temBook : arrBook){
			if(temBook.getName().contains(subName))
			{
				searchedBook.add(temBook);
			}
		}

		if(searchedBook.isEmpty() == false){
			return searchedBook;
		}

		return null;
	}

	// Constructor
	public ArrayListBookDataAccessDaoImpl() {
		// TODO Auto-generated constructor stub
		/*
		Book testBook1 = new Book(100, "java1", "james", "Sanfran");
		Book testBook2 = new Book(101, "cplus", "john", "Seoul");
		Book testBook3 = new Book(102, "1Q84", "marry", "Japan");
		arrBook.add(testBook1);
		arrBook.add(testBook2);
		arrBook.add(testBook3);
		*/
	}

}
