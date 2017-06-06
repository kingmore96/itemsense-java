package com.impinj.itemsense.client.coordinator.readerconfiguration;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelConfig {

  private int channelIndex;
  private double[] txFrequenciesInMhz;

}
