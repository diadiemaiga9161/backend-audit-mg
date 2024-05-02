package com.Projet.Projet.Message;

public class MessageResponse {
    private String message;
    private Boolean status;



    public MessageResponse(String message, boolean status) {
        this.message = message;
        this.status=status;
    }

    public String getMessage() {
        return message;
    }


    public static Object set(String message, boolean status) {
        return new MessageResponse(message, status);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}