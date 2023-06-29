package project.com.hotplace.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import project.com.hotplace.shop.model.ShopVO;
import project.com.hotplace.shop.service.ShopService;

@Slf4j
@Controller
@RequestMapping("/json/shop")
public class ShopRestController {

	@Autowired
	ShopService service;
	
	@ResponseBody
	@RequestMapping(value = "/selectAll.do", method = RequestMethod.GET)
	public List<ShopVO> json_selectAll(String searchKey, String searchWord, int pageNum) {
		log.info("/json_selectAll.do");
		log.info("searchKey:{}", searchKey);
		log.info("searchKey:{}", searchWord);
		
		List<ShopVO> vos = service.searchList(searchKey, searchWord, pageNum);
		
		return vos;
	}
}