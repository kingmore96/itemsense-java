package com.impinj.itemsense.client.data.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jcombopi on 1/26/16.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemResponse {

    @JsonProperty("items")
    private Item[] items;

    @JsonProperty("nextPageMarker")
    private String nextPageMarker;
}
