package com.example.roster10;

public class RosterNotFoundException extends RuntimeException {
    public RosterNotFoundException(String massage) {
        super(massage);
    }
}
