package project.com.hotplace.shop.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ShopDAOimpl implements ShopDAO {
	
	@Autowired
	SqlSession sqlSession;

	public ShopDAOimpl() {
		log.info("shopDAOimpl....");
	}
	
	@Override
	public int insert(ShopVO vo) {
		log.info("insert()....{}", vo);
		return sqlSession.insert("SHO_INSERT",vo);
	}

	@Override
	public int update(ShopVO vo) {
		log.info("update()....{}", vo);
		return sqlSession.update("SHO_UPDATE",vo);
	}

	@Override
	public int delete(ShopVO vo) {
		log.info("delete()....{}", vo);
		return sqlSession.delete("SHO_DELETE",vo);
	}

	@Override
	public List<ShopVO> selectAll() {
		log.info("selectAll()....{}");
		return sqlSession.selectList("SHO_SELECT_ALL");
	}

	@Override
	public ShopVO selectOne(ShopVO vo) {
		log.info("selectOne()....{}", vo);
		return sqlSession.selectOne("SHO_SELECT_ONE",vo);
	}

	@Override
	public List<ShopVO> searchList(String searchKey, String searchWord, int pageNum) {
		log.info("searchList()....searchKey:{}",searchKey);
		log.info("searchList()....searchWord:{}",searchWord);
		log.info("searchList()....pageNum:{}", pageNum);
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		int itemsPerPage = 10;
		int end = itemsPerPage * pageNum;
		int start = (pageNum - 1) * itemsPerPage + 1;
		
		param.put("end", end);
		param.put("start", start);
		
		if(!searchWord.isEmpty()) {
			param.put("searchKey", searchKey);
			param.put("searchWord", "%" + searchWord + "%");
			return sqlSession.selectList("SHO_SEARCH_LIST", param);
		}else {
			return sqlSession.selectList("SHO_SELECT_ALL", param);
		}
	}

}