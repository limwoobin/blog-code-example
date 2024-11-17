package org.example.ormbulkinsert.fixture;

import org.example.ormbulkinsert.domain.Location;

public class LocationFixture {

  public static Location LOCATION_GANGNAM() {
    return Location.builder()
      .country("korea")
      .city("seoul")
      .addr("gangnam")
      .zipCode(12345)
      .build();
  }

  public static Location LOCATION_GURO() {
    return Location.builder()
      .country("korea")
      .city("seoul")
      .addr("guro")
      .zipCode(54321)
      .build();
  }
}
