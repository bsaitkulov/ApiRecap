package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Arrays;
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Votes {
    @JsonInclude(JsonInclude.Include.NON_NULL)

    @JsonProperty("id")
    String id;
    @JsonProperty("image_id")
    String imageId;
    @JsonProperty("sub_id")
    String subId;
    @JsonProperty("value")
    String value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("country_code")
    String countryCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    String message;
//    @JsonProperty("image")
//    Image [] images;
}
