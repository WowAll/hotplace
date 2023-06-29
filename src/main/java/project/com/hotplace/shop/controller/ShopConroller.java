package project.com.hotplace.shop.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import project.com.hotplace.shop.model.ShopVO;
import project.com.hotplace.shop.service.ShopService;

@Slf4j
@Controller
@RequestMapping("shop")
public class ShopConroller {

	@Autowired
	ShopService service;
	
	@Autowired
	ServletContext sContext;
	
	@RequestMapping(value = "/selectAll.do", method = RequestMethod.GET)
	public String selectAll(Model model) {
		log.info("/selectAll.do");

		List<ShopVO> vos = service.selectAll();

		model.addAttribute("vos", vos);
		
		log.info("data...{}", vos);

		return null;
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.GET)
	public String insert() {
		log.info("/shop/insert.do");

		return "shop/insert";
	}
	
	@RequestMapping(value = "/insertOK.do", method = RequestMethod.POST)
	public String insertOK(ShopVO vo) {
		log.info("/insertOK.do...{}", vo);
		
		int result = service.insert(vo);
		log.info("result...{}", result);
		
		return "redirect:insert.do";
		
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String update(ShopVO vo, Model model) {
		log.info("/update.do...{}", vo);

		ShopVO vo2 = service.selectOne(vo);

		model.addAttribute("vo2", vo2);

		return "shop/update";
	}
	
	@RequestMapping(value = "/updateOK.do", method = RequestMethod.POST)
	public String updateOK(ShopVO vo) {
		log.info("/b_updateOK.do...{}", vo);
		
		int result = service.update(vo);
		log.info("result...{}", result);
		
		if(result==1) {
			return "redirect:selectOne.do?num="+vo.getNum();
		}else {
			return "redirect:update.do?num="+vo.getNum();
		}
		
	}
	
	@RequestMapping(value = "/deleteOK.do", method = RequestMethod.GET)
	public String deleteOK(ShopVO vo) {
		log.info("/b_deleteOK.do...{}", vo);
		
		int result = service.delete(vo);
		log.info("result...{}", result);
		
		if(result==1) {
			return "redirect:selectAll.do";
		}else {
			return "redirect:selectOne.do?num="+vo.getNum();
		}
		
	}
	
	@RequestMapping(value = "/searchList.do", method = RequestMethod.GET)
	public String searchList(Model model, String searchKey, String searchWord) {
		log.info("/searchList.do");
		log.info("searchKey:{}",searchKey);
		log.info("searchWord:{}",searchWord);
		
		List<ShopVO> vos = service.searchList(searchKey,searchWord);
		
		model.addAttribute("vos", vos);
		
		return "shop/selectAll";
	}
	
	@RequestMapping(value = "/searchList.do", method = RequestMethod.GET)
	public String b_searchList(Model model,
			String searchKey, String searchWord) {
		log.info("/searchList.do");
		log.info("searchKey:{}",searchKey);
		log.info("searchWord:{}",searchWord);
		
		List<ShopVO> vos = service.searchList(searchKey,searchWord);
		
		model.addAttribute("vos", vos);
		
		return "Shop/selectAll";
	}
	
	@RequestMapping(value = "/selectOne.do", method = RequestMethod.GET)
	public String b_selectOne(ShopVO vo, Model model) {
		log.info("/b_selectOne.do...{}", vo);
		
		ShopVO vo2 = service.selectOne(vo);
		model.addAttribute("vo2", vo2);
		

		return "Shop/selectOne";
	}
	
}
