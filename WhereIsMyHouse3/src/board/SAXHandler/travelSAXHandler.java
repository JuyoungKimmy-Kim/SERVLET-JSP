package board.SAXHandler;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import board.dto.travelDto;

public class travelSAXHandler extends DefaultHandler {
  private List<travelDto> travelList = new ArrayList<>();

  private String temp;
  private travelDto travel;

  public void startElement(String uri, String localName, String qName, Attributes att) {
    if (qName.equals("item")) {
      travel = new travelDto();
    }
  }

  public void endElement(String uri, String localName, String qName) {

    if (qName.equals("LAT")) {
      travel.setLat(Float.parseFloat(temp));
    } else if (qName.equals("LNG")) {
      travel.setLng(Float.parseFloat(temp));
    } else if (qName.equals("PLACE")) {
      travel.setPlace(temp);
    } else if (qName.equals("TITLE")) {
      travel.setTitle(temp);
    } else if (qName.equals("TRFC_INFO")) {
      travel.setTRFC_INFO(temp);
    }

    else if (qName.equals("item")) {
      travelList.add(travel);
    }
  }

  public void characters(char[] ch, int start, int length) {
    temp = new String(ch, start, length);
  }

  public List<travelDto> getTravelList() {
    return travelList;
  }
}
