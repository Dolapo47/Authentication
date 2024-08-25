package com.softaliance.authentication.services;

import com.softaliance.authentication.dto.Employees;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Employees fetchUserByEmail(String email) {
        String url = "http://localhost:8081/auth/get/email/";
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(restTemplate.getForObject(url + email, String.class));
            if (jsonObject.get("data") != null) {
                JSONObject data = (JSONObject) jsonObject.get("data");
                return Employees.builder()
                        .email((String) data.get("email"))
                        .password((String) data.get("password"))
                        .address((String) data.get("address"))
                        .firstName((String) data.get("firstName"))
                        .lastName((String) data.get("lastName"))
                        .build();
            }
            return null;
        }catch (Exception e){
            logger.info("Error while fetching user by email: {}", e.getMessage());
            return null;
        }
    }
}
