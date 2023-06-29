package project.com.hotplace.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import project.com.hotplace.shop.model.ShopDAO;
import project.com.hotplace.shop.model.ShopVO;

@Slf4j
@Service
public class ShopService {
	
	@Autowired
	ShopDAO dao;
	
	public ShopService() {
		log.info("ShopService...");
	}
	
	public int insert(ShopVO vo) {
		return dao.insert(vo);
	}
	
	public int delete(ShopVO vo) {
		return dao.delete(vo);
	}
	
	public int update(ShopVO vo) {
		return dao.update(vo);
	}
	
	public List<ShopVO> selectAll() {
		return dao.selectAll();
	}
	
	public ShopVO selectOne(ShopVO vo) {
		return dao.selectOne(vo);
	}
	
	public List<ShopVO> searchList(String searchKey, String searchWord, int pageNum) {
		return dao.searchList(searchKey, searchWord, pageNum);
	}
}