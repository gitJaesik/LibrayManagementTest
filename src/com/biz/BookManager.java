package com.biz;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import com.dao.ArrayListBookDataAccessDaoImpl;
import com.dao.BookDataAccessDao;
import com.dto.Book;
import com.view.FormatWriter;
import com.view.InputValueReader;
import com.view.Menu;
import com.view.Message;



//�������� �ý��� �����κ�
public class BookManager
{
	//�Ʒ��� ��ü �����ܿ� �߰����� ������~
	InputValueReader reader = new InputValueReader();
	BookDataAccessDao dao = new ArrayListBookDataAccessDaoImpl();
	//Menu menu = new Menu();
	//Message message = new Message();
	//FormatWriter fw = new FormatWriter();
	ArrayList<Book> searchedBook;

	public void start(){

		while(true)
			showMenu();

	}

	public void showMenu(){
		// show menu
		FormatWriter.showMenu(Menu.MENU01);
		FormatWriter.showMessage(Message.MESSAGE09);
		
		// input int to select number
		switch(reader.readIntValue()){
		case 1:
			// 저장 
			saveB();
			// 저장 완료
			break;
		case 2:
			FormatWriter.showMessage(Message.MESSAGE03);
			// 검색 
			searchB();
			break;
		case 3:
			//FormatWriter.showMessage(Message.MESSAGE05);
			// 전체 출력 
			//FormatWriter.showMenu(Menu.MENU02);
			searchBAll();
			break;
		case 4:
			FormatWriter.showMessage(Message.MESSAGE18);
			// 종료 
			FormatWriter.showMenu(Menu.MENU02);
			if(reader.readIntValue() == 1){
				// 종료 
				System.out.println("종료되었습니다.");
				System.exit(0);
			}
			break;
		default :
			FormatWriter.showMessage(Message.MESSAGE12);
			break;
		}
	}

	//TODO 한글 에러 고치기 
	public void saveB(){
		Object createBook = null;
		FormatWriter.showMessage(Message.MESSAGE11);
		createBook = reader.readBookData();
		while(true){
			if(createBook != null){
				// 저장 묻는 메세지 
				FormatWriter.showMessage(Message.MESSAGE01);
				FormatWriter.showMenu(Menu.MENU02);
				if(reader.readIntValue() == 1){
					if(dao.saveBook((Book)createBook) == true){
						FormatWriter.showMessage(Message.MESSAGE02);
						break;
					}
				}else{
					FormatWriter.showMessage(Message.MESSAGE12);
					// 다시 메뉴로
					break;
				}
			}else{
				FormatWriter.showMessage(Message.MESSAGE15);
				FormatWriter.showMessage(Message.MESSAGE11);
				createBook = reader.readBookData();
			}
		}
	}
	
	public void searchB(){
		
		//search will be conducted by only BookName
		//if(reader.readStringValue() != ""){} // to search all when user just input enter
		// It's error point
		searchedBook = dao.searchBookByName(reader.readStringValue());
		if(searchedBook != null){
			FormatWriter.showBookList(searchedBook);
			System.out.println("총 "+ searchedBook.size() +"권이 검색되었습니다.");
			
			// To go secondMenu
			while(true){
				if(afterSearch() == false){
					break;
				}
			}
		}else{
			FormatWriter.showMessage(Message.MESSAGE19);
			// 다시 메뉴로
		}
	}
	
	public boolean afterSearch(){
		FormatWriter.showMenu(Menu.MENU03);
		FormatWriter.showMessage(Message.MESSAGE09);
		switch(reader.readIntValue()){
		case 1:
			//FormatWriter.showMessage(Message.MESSAGE05);
			selectB();
			// 수정 
			//break;
			return false;
		case 2:
			// 삭제 
			deleteB();
			//ToDo
			return false;
		case 3:
			return false;
			// 주메뉴로 가기 
		case 4:
			System.exit(0);
			// 종료 
		default :
			FormatWriter.showMessage(Message.MESSAGE16);
			break;
		}
		return true;
	}

	public void searchBAll(){
		FormatWriter.showBookList(dao.selectAll());
	}

	//select update part
	public void selectB(){
		FormatWriter.showMessage(Message.MESSAGE10);
		int bookNo;
		int modifyPart;
		String modifyContent;
		Map<Integer, String> updateContent = new Hashtable<Integer, String>();

		// 수정할 책 번호 입력
		bookNo = reader.readIntValue();
		// 수정할 책 번호가 존재하는지 확인 
		if(bookNo <= searchedBook.size()){
			while(true){
				FormatWriter.showMenu(Menu.MENU04);
				FormatWriter.showMessage(Message.MESSAGE13);

				// 수정할 파트 입력 ( 책이름 / 저자 / 출판사)
				modifyPart = reader.readIntValue();
				FormatWriter.showMessage(Message.MESSAGE17);

				// 수정할 내용 읿력 ( String )
				modifyContent = reader.readStringValue();
				updateContent.put(modifyPart, modifyContent);


				// 더 수정하겠습니까?
				FormatWriter.showMessage(Message.MESSAGE14);
				FormatWriter.showMenu(Menu.MENU02);
				//Yes
				if(reader.readIntValue() == 1){
					continue;
				}else{
					//No
					break;
				}
			}
		}else{
			FormatWriter.showMessage(Message.MESSAGE16);
			FormatWriter.showMessage(Message.MESSAGE10);
		}
		

		FormatWriter.showMessage(Message.MESSAGE05);
		FormatWriter.showMenu(Menu.MENU02);
		if(reader.readIntValue() == 1){
			// while 끝난 후 ( 수정할 내용 다 입력 받은 후 )
			// 수정하는 함수 호출 ( updateContent 데이터 예시 : {1, java }, {3, house}
			if(dao.updateBook(bookNo, updateContent) != false){
				FormatWriter.showMessage(Message.MESSAGE06);
			}else{
				FormatWriter.showMessage(Message.MESSAGE15);
			}
			// 함수 내용은 updateContent 개수대로 확인한 다음에 고치기..
		}
	}
	
	public void deleteB(){
		FormatWriter.showMessage(Message.MESSAGE10);
		int deleteNum = reader.readIntValue();
		FormatWriter.showMessage(Message.MESSAGE07);
		if(reader.readIntValue() == 1){
			if(dao.deleteBook(deleteNum) == true){
				FormatWriter.showMessage(Message.MESSAGE08);
			}
		}else{
			FormatWriter.showMessage(Message.MESSAGE12);
		}
	}
}

















