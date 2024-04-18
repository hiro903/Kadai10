package com.example.roster10;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RosterController {
    private final RosterService rosterService;

    public RosterController(RosterService rosterService) {
        this.rosterService = rosterService;
    }

    @PostMapping("/roster")
    public ResponseEntity<RosterResponse> insert(@RequestBody RosterRequest rosterRequest, UriComponentsBuilder uriBuilder) {
        Roster roster = rosterService.insert(rosterRequest.getName(), rosterRequest.getDateOfBirth(), rosterRequest.getNearestStation());
        URI location = uriBuilder.path("/roster/{id}").buildAndExpand(roster.getId()).toUri();
        RosterResponse body = new RosterResponse("roster created");
      return ResponseEntity.created(location).body(body);
        //createdメソッドを使うと201で返せる(location)を渡すのは決まったやり方。body(body)にはnewしたインスタンスを渡す

    }


    @ExceptionHandler(value = RosterNotFoundException.class)
    public ResponseEntity handleRosterNotFoundException(
            RosterNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("timestamp", ZonedDateTime.now().toString());
        body.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("message", e.getMessage());
        body.put("path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
