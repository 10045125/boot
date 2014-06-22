package jp.co.answernet.boot.core.values;

import java.io.Serializable;

/**
 * Created by yasuhiro on 2014/05/18.
 */
public class Place implements Serializable {

  Double lat; // latitude

  Double lng; // longitude

  String address;


  public Place(Double lat, Double lng, String address) {
    this.lat = lat;
    this.lng = lng;
    this.address = address;
  }

  public Double getLat() {
    return lat;
  }

  public Double getLng() {
    return lng;
  }

  public String getAddress() {
    return address;
  }

}
