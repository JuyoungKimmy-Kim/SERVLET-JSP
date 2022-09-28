package board.dto;

public class travelDto {
  private String title;
  private String dong;
  private float lat;
  private float lng;
  private String place;
  private String TRFC_INFO;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDong() {
    return dong;
  }

  public void setDong(String dong) {
    this.dong = dong;
  }

  public float getLat() {
    return lat;
  }

  public void setLat(float lat) {
    this.lat = lat;
  }

  public float getLng() {
    return lng;
  }

  public void setLng(float lng) {
    this.lng = lng;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getTRFC_INFO() {
    return TRFC_INFO;
  }

  public void setTRFC_INFO(String TRFC_INFO) {
    this.TRFC_INFO = TRFC_INFO;
  }

  @Override
  public String toString() {
    return "travelDto [title=" + title + ", dong=" + dong + ", lat=" + lat + ", lng=" + lng
        + ", place=" + place + ", TRFC_INFO=" + TRFC_INFO + "]";
  }


}
