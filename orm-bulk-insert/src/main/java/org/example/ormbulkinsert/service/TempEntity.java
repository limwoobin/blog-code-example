package org.example.ormbulkinsert.service;

import jakarta.persistence.*;

@Entity
@Table(name = "temp")
public class TempEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

}
