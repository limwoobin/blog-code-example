package org.example.ormbulkinsert.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

  @Column(name = "country")
  private String country;

  @Column(name = "city")
  private String city;

  @Column(name = "addr")
  private String addr;

  @Column(name = "zip_code")
  private int zipCode;

  @Column(name = "etc")
  private String etc;

  @Builder
  public Location(String country, String city, String addr, int zipCode, String etc) {
    this.country = country;
    this.city = city;
    this.addr = addr;
    this.zipCode = zipCode;
    this.etc = etc;
  }
}
