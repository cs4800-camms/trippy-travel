package com.samm.trippytravel.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.samm.trippytravel.payload.request.search.SearchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/yelp")
@Slf4j
public class YelpController {
    private static final String API_KEY = "5ODNo7dkv7sMRuTITRs89vWSfw2sisrSEr3TJBHTGqUi1Ptm6IM1gNWBTClhKDqPk1stysMjM8SO8vskW7pPwCRfTlH0DeMB0fRCiWIrVfcwfk5wtsfryzLUkdpzY3Yx";

    @PostMapping("/get-results")
    public String testYelpFusion(
            @RequestBody SearchRequest searchRequest) throws URISyntaxException, JsonProcessingException {
        String term = searchRequest.getTerm().replaceAll("\\s", "+");
        String location = searchRequest.getLocation().replaceAll("\\s", "+");
        URI yelp_url = new URI(
                "https://api.yelp.com/v3/businesses/search?term=" + term + "&location=" + location);

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<?> request = RequestEntity.get(yelp_url).header("Authorization", "Bearer " + API_KEY).build();
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        return response.getBody();
    }
}

