package study.cloud.stc.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import study.cloud.stc.common.paging.Paging;
import study.cloud.stc.product.model.service.ProductService;
import study.cloud.stc.product.model.vo.ProductDetailDto;
import study.cloud.stc.product.model.vo.ProductVo;
import study.cloud.stc.qna.model.service.QnaService;
import study.cloud.stc.qna.model.vo.QnaVo;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	@Autowired
	private QnaService qna_service;
	
	@GetMapping
	public ModelAndView productList(ModelAndView mv
			, ProductVo vo
			, @RequestParam(value="page", defaultValue="1") int page
			) throws Exception {
		int currentPage = page; 
		int totalCnt= service.selectCount(vo); 
		Map<String, Integer> map= new Paging().paging(currentPage, totalCnt, 8, 3); 
		mv.addObject("pageInfo", map);
		mv.addObject("pdList", service.selectList(currentPage, 8, vo));
		mv.setViewName("product/product");
		return mv;
	}
	
	@GetMapping("/map")
	public ModelAndView productMap(ModelAndView mv
			, ProductVo vo
			) throws Exception {
		mv.addObject("pdList", service.selectList(vo));
		mv.setViewName("product/map");
		return mv;
	} 
	
	@GetMapping("/detail")
	public ModelAndView productDetail(
			ModelAndView mv
			, @RequestParam(value="page", defaultValue="1") int page
			, @RequestParam(value="proNum" ,defaultValue = "") int proNum
			) throws Exception {
		int currentPage = page; 
		int totalCnt= qna_service.selectCount(proNum);
		Map<String, Integer> map = new Paging().paging(currentPage, totalCnt, 3, 5); 
		mv.addObject("pageInfo", map);
		ProductDetailDto dto = service.selectOne(proNum);
		mv.addObject("detail", dto);
		mv.addObject("qnaList", qna_service.selectQnaList(currentPage, 3, proNum));
		mv.setViewName("product/detail");
		return mv;
	}
	
	@PostMapping("/detail/qnainsert")
	@ResponseBody
	public String insertProductQna(QnaVo vo
			) throws Exception {

		qna_service.insert(vo);
		List<QnaVo> qnaList = qna_service.selectQnaList(vo.getProNum());
		return new Gson().toJson(qnaList);

	}
	
	@PostMapping("/detail/qnaupdate")
	@ResponseBody
	public String updateQna(QnaVo vo
			,@RequestParam(value="qnaNum") int qnaNum
			,@RequestParam(value="proNum") int proNum
			) throws Exception {
		
		qna_service.update(vo);
		List<QnaVo> qnaList = qna_service.selectQnaList(vo.getProNum());
		return new Gson().toJson(qnaList);

	}
	
	@PostMapping("/detail/qnadelete")
	@ResponseBody
	public String deleteQna(QnaVo vo
			,@RequestParam(value="qnaNum", defaultValue = "") int qnaNum
			,@RequestParam(value="proNum", defaultValue = "") int proNum
			) throws Exception{
		qna_service.delete(qnaNum);
		List<QnaVo> qnaList = qna_service.selectQnaList(vo.getProNum());
		return new Gson().toJson(qnaList);
	}
	
	@PostMapping("/detail/qnareply")
	@ResponseBody
	public String replyQna(QnaVo vo
			,@RequestParam(value="qnaNum", defaultValue = "") int qnaNum
			,@RequestParam(value="proNum", defaultValue = "") int proNum
			) throws Exception{
		qna_service.updateReply(vo);
		List<QnaVo> qnaList = qna_service.selectQnaList(vo.getProNum());
		return new Gson().toJson(qnaList);
	}
	
}
