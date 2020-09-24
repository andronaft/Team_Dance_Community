package com.zuk.rest;

import com.zuk.dto.deep.DeepDto;
import com.zuk.model.DeepLink;
import com.zuk.service.DeepLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/deep/")
public class DeepLinkRestV1 {
    private final DeepLinkService deepLinkService;

    @Autowired
    public DeepLinkRestV1(DeepLinkService deepLinkService) {
        this.deepLinkService = deepLinkService;
    }

    @PostMapping("save")
    public ResponseEntity save(@RequestBody DeepDto deepDto){

        Map<Object, Object> response = new HashMap<>();
        System.out.println(deepDto.getFrom() + deepDto.getUrl_webview());
        System.out.println(deepDto.ToDeep().toString());
        DeepLink deepLink = deepDto.ToDeep();
        response.put("deepSave", deepLinkService.save(deepLink));

        return ResponseEntity.ok(response);
    }

    @GetMapping("getall")
    public ResponseEntity getAll(){

        Map<Object, Object> response = new HashMap<>();

        response.put("deepSave", deepLinkService.findAll());

        return ResponseEntity.ok(response);
    }

    @GetMapping("getbyfromconsist")
    public ResponseEntity getByFromConsist(@RequestParam String string){
        Map<Object, Object> response = new HashMap<>();

        response.put("deepSave", deepLinkService.findAllByFromConsist(string));

        return ResponseEntity.ok(response);
    }
}
