package com.example.roster10;

public class StaffNotFoundException extends RuntimeException {
    public StaffNotFoundException(String massage) {
        super(massage);
    }
}
