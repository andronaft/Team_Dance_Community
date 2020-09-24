package com.zuk.dto.deep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zuk.model.DeepLink;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeepDto {
    private String from;
    private String url_webview;

    public DeepLink ToDeep(){
        DeepLink deepLink = new DeepLink();
        deepLink.setFrom(from);
        deepLink.setUrl_webview(url_webview);
        return deepLink;
    }
}
