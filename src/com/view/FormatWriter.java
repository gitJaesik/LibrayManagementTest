package com.view;

import java.util.ArrayList;

import com.dto.Book;

public class FormatWriter {

	public static void showMenu(String menu){
		
		System.out.println("=======================");
		System.out.println(menu);
		System.out.println("=======================");
	}	
	
	public static void showMessage(String message){
		
		System.out.println(message);		
	}
	
	public static void showBookList(ArrayList<Book> bookList){

		for(Book b : bookList){
			System.out.println("================================");
			System.out.println("책번호 : "+b.getNo());
			System.out.println("책제목 : "+b.getName());
			System.out.println("저 자 : "+b.getAuthor());
			System.out.println("출판사 : "+b.getPublisher());
			System.out.println("================================");
		}
	}
}
