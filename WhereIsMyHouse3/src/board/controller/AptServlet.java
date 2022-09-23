package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.dto.DongDto;
import board.dto.GugunDto;
import board.dto.HouseInfoDto;
import board.dto.SidoDto;
import board.service.DongService;
import board.service.DongServiceImpl;
import board.service.GugunService;
import board.service.GugunServiceImpl;
import board.service.HouseInfoService;
import board.service.HouseInfoServiceImpl;
import board.service.SidoService;
import board.service.SidoServiceImpl;


@WebServlet("/apt/*")
public class AptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	SidoService sidoService=SidoServiceImpl.getInstance();
	GugunService gugunService=GugunServiceImpl.getInstance();
	DongService dongService=DongServiceImpl.getInstance();
	HouseInfoService houseInfoService=HouseInfoServiceImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String contextPath=request.getContextPath();
		String path=request.getRequestURI().substring(contextPath.length());
		System.out.println(path);
		
		switch (path) {
		case "/apt/aptSidoList" : sidoList(request, response); break;
		case "/apt/aptGugunList" : gugunList (request, response); break;
		case "/apt/aptDongList" : dongList(request, response); break;
		case "/apt/aptListByDong" : aptListByDong(request, response); break;
		case "/apt/aptListByName" : aptListByName(request, response); break;
		}
	}
	
	private void sidoList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("================Enter Sido List ====================");
		
		List<SidoDto> sidoList=null;

		sidoList=sidoService.sidoList();
		
		Gson gson=new Gson();
		String jsonStr=gson.toJson(sidoList);
		
		System.out.println(jsonStr);
		response.getWriter().write(jsonStr);
	}
	
	private void gugunList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("================Enter Gugun List ====================");
		
		List<GugunDto> gugunList=null;
		
		String sidoCode=request.getParameter("sidoCode");
		gugunList=gugunService.gugunList(sidoCode);
		
		Gson gson=new Gson();
		String jsonStr=gson.toJson(gugunList);
		
		System.out.println(jsonStr);
		response.getWriter().write(jsonStr);
	}
	
	private void dongList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("================Enter Dong List ====================");
		
		List<DongDto> dongList=null;
		
		String gugunCode=request.getParameter("gugunCode");		
		dongList=dongService.dongList(gugunCode);
		
		Gson gson=new Gson();
		String jsonStr=gson.toJson(dongList);
		
		System.out.println(jsonStr);
		response.getWriter().write(jsonStr);
	}
	
	private void aptListByDong(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("================Enter APT List (Dong) ====================");
		
		List<HouseInfoDto> houseInfoList=null;
		
		String dongName=request.getParameter("dongName");		
		houseInfoList=houseInfoService.houseInfoListByDong(dongName);
		
		Gson gson=new Gson();
		String jsonStr=gson.toJson(houseInfoList);
		
		System.out.println(jsonStr);
		response.getWriter().write(jsonStr);
	}
	
	private void aptListByName(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("================Enter APT List (APT Name)====================");
		
		List<HouseInfoDto> houseInfoList=null;
		
		String aptName=request.getParameter("aptName");		
		houseInfoList=houseInfoService.houseInfoListByName(aptName);
		
		Gson gson=new Gson();
		String jsonStr=gson.toJson(houseInfoList);
		
		System.out.println(jsonStr);
		response.getWriter().write(jsonStr);
	}

}
