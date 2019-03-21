package com.ankuran.wages.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ankuran.wages.model.response.CentreResponseDTO;
import com.ankuran.wages.model.response.CentresResponseDTO;

@Controller
@RequestMapping("/centres")
public interface CentreResource {

    @GetMapping("/{id}")
    public ResponseEntity<CentreResponseDTO> getCentreId(@PathVariable("id") long centreId);
    
    @RequestMapping("/")
    public ResponseEntity<CentresResponseDTO> getCentres();

}

