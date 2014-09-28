package com.view;
//����â���� �Է°��� �д� Ŭ����
// 한글 테스트입니다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.dto.Book;

public class InputValueReader
{
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public int readIntValue(){
		
		int intValue=0;
		
		try
		{
			intValue = Integer.parseInt(in.readLine());
		}
		catch (IOException ioe)
		{
			
		}

		return intValue;
	}

	
	public String readStringValue(){
		
		String stringValue="";
		
		try
		{
			stringValue = in.readLine();
		}
		catch (IOException ioe)
		{
		}

		return stringValue;
	}
	
	public Book readBookData(){
		
		try
		{
			String [] bookInfo = null;
			
			if((bookInfo = in.readLine().split("/"))!=null){
				
				Book book = new Book();				
				
				book.setName(bookInfo[0]);
				book.setAuthor(bookInfo[1]);
				book.setPublisher(bookInfo[2]);
				
				return book;
			}
			
		}
		catch (IOException ioe)
		{
		
		}
		
		return null;
	}
}