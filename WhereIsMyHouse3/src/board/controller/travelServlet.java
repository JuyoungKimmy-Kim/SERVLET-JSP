package board.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.google.gson.Gson;
import board.SAXHandler.travelSAXHandler;
import board.dto.travelDto;

/**
 * Servlet implementation class travel
 */
@WebServlet("/travel")
public class travelServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private final String SERVICE_URL =
      "http://apis.data.go.kr/6260000/AttractionService/getAttractionKr";
  private final String SERVICE_KEY =
      "oROX7FP%2BdQMrLZhV3ECvmW9igibkHhrwiz8kH2KdZWoleQw%2F2%2FVlEdrwralbOxafDz94MmZ5fB1Z3EJavGKFtg%3D%3D";



  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    String pageNo = request.getParameter("pageNo");
    if (pageNo == null)
      pageNo = "1";

    String numOfRows = request.getParameter("numOfRows");
    if (numOfRows == null)
      numOfRows = "10";


    // clsf xml, json 구분 client 에게 가공할 것인지 여부
    String clsf = request.getParameter("clsf");
    if (clsf == null)
      clsf = "json";

    StringBuilder sb = new StringBuilder();
    sb.append(SERVICE_URL).append("?serviceKey=").append(SERVICE_KEY).append("&pageNo=")
        .append(pageNo).append("&numOfRows=").append(numOfRows).append("&resultType=")
        .append("xml");


    URL url = new URL(sb.toString());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    System.out.println(con.getResponseCode());

    BufferedReader br = null;
    if (con.getResponseCode() == 200) {
      br = new BufferedReader(new InputStreamReader(con.getInputStream()));
    } else { // 200이 아닌 경우
      br = new BufferedReader(new InputStreamReader(con.getErrorStream()));

    }

    // api response => StringBuilder result에 넣는다.
    StringBuilder result = new StringBuilder();
    String line;
    while ((line = br.readLine()) != null) {
      result.append(line);
    }
    br.close();
    con.disconnect();

    // api response 확인
    System.out.println(result);

    if ("raw".equals(clsf)) {
      sendRaw(response, result.toString());
    } else if ("json".equals(clsf)) {
      sendJson(response, result.toString());
    }

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  private void sendRaw(HttpServletResponse response, String result)
      throws ServletException, IOException {

    response.setContentType("application/xml; charset=utf-8");


    // xml -> java ArrayList 객체로 필요한 항복만 추출
    try {
      // SAX parser
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();
      travelSAXHandler handler = new travelSAXHandler();

      // 한글처리에 대한 부분
      InputStream is = new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8));
      parser.parse(is, handler);

      List<travelDto> travelList = handler.getTravelList();
      for (travelDto houseDeal : travelList) {
        System.out.println(houseDeal);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }


    response.getWriter().append(result);

  }


  private void sendJson(HttpServletResponse response, String result)
      throws ServletException, IOException {

    // xml=> Json
    response.setContentType("application/json; charset=utf-8");


    // xml => java ArrayList 객체로 필요한 항목만 추출 SAX parser
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();

      travelSAXHandler handler = new travelSAXHandler();

      // 한글처리에 대한 부분 ("utf-8")이 아닌 경우에 변경하는 작업
      InputStream is = new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8));
      parser.parse(is, handler);

      List<travelDto> travelList = handler.getTravelList();

      for (travelDto houseDeal : travelList)
        System.out.println(houseDeal);

      Gson gson = new Gson();
      String jsonStr = gson.toJson(travelList);
      response.getWriter().append(jsonStr);


    } catch (Exception e) {
      e.printStackTrace();
    }


  }



}


