package com.impinj.itemsense.client.coordinator.readerconfiguration;

import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class ReaderConfiguration {

  private String name;

  @JsonTypeId
  private Operation operation;
  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
      include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "operation", visible = true)
  private ReaderConfigurationDetails configuration;

  //For some reason using @AllArgsConstructor screws up Jackson deserialization
  public ReaderConfiguration(
      String name,
      Operation operation,
      ReaderConfigurationDetails readerConfigurationDetails) {
    this.name = name;
    this.operation = operation;
    this.configuration = readerConfigurationDetails;
  }
}


