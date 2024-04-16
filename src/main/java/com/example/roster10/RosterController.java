package com.example.roster10;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class RosterController {
    private final RosterService rosterService;

    public RosterController(RosterService rosterService) {
        this.rosterService = rosterService;
    }

    @PostMapping("/roster")
    public ResponseEntity<Object> insert(@RequestBody RosterRequest rosterRequest, UriComponentsBuilder uriBuilder) {
        Roster roster = rosterService.insert(rosterRequest.getName(), rosterRequest.getDateOfBirth(), rosterRequest.getNearestStation());
        URI location = uriBuilder.path("/roster/{id}").buildAndExpand(roster.getId()).toUri();
        RosterResponse body = new RosterResponse("roster created");
        return ResponseEntity.created(location).body(body);
        //createdメソッドを使うと201で返せる(location)を渡すのは決まったやり方。body(body)にはnewしたインスタンスを渡す

    }
}
