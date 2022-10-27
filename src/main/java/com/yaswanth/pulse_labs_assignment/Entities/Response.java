package com.yaswanth.pulse_labs_assignment.Entities;

import org.springframework.stereotype.Component;

@Component
public class Response {
    private int statusCode;
    private String statusMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "statusCode=" + statusCode +
                ", statusMessage=" + statusMessage +
                '}';
    }
}
