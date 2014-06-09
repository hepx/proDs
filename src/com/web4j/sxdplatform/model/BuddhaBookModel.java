package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.BuddhaBookDao;
import com.web4j.sxdplatform.entity.TbBuddhaBook;
import com.web4j.sxdplatform.pojo.BuddhaBook;

@Service("buddhaBookModel")
public class BuddhaBookModel {

	@Autowired
	private BuddhaBookDao buddhaBookDao;
	
	public void createBook(BuddhaBook buddhaBook)throws Exception{
		TbBuddhaBook tbBuddhaBook=new TbBuddhaBook();
		BeanUtils.copyProperties(buddhaBook, tbBuddhaBook);
		tbBuddhaBook.setCreateTime(new Date());
		buddhaBookDao.save(tbBuddhaBook);
	}
	
	public List<BuddhaBook> queryBooks(Integer start,Integer limit)throws Exception{
		List<TbBuddhaBook> tbBuddhaBooks=buddhaBookDao.queryTbBuddhaBooks(start, limit);
		List<BuddhaBook> buddhaBooks=new ArrayList<BuddhaBook>();
		for(TbBuddhaBook tbBuddhaBook:tbBuddhaBooks){
			BuddhaBook buddhaBook = new BuddhaBook();
			BeanUtils.copyProperties(tbBuddhaBook, buddhaBook);
			buddhaBooks.add(buddhaBook);
		}
		return buddhaBooks;
	}
	
	public Long getBookCount(){
		return buddhaBookDao.counts();
	}
	
	public void deleteBooks(List<BuddhaBook> buddhaBooks)throws Exception{
		for(BuddhaBook book:buddhaBooks){
			if(book.getBookId()!=null&&!"".equals(book.getBookId())){
				buddhaBookDao.deleteById(book.getBookId());
			}
		}
	}
	
	public TbBuddhaBook getTbBuddhaBookById(Long bookId)throws Exception{
		return buddhaBookDao.get(bookId); 
	}
	
	public void updateTbBuddhaBook(TbBuddhaBook tbBuddhaBook) throws Exception{
		buddhaBookDao.update(tbBuddhaBook);
	}
	
	public List<TbBuddhaBook> queryBooksOfPhone(Integer start,Integer limit) throws Exception{
		return buddhaBookDao.queryTbBuddhaBooksOfPhone(start, limit);
	}
}
