package org.prod.marong.model;

public class ResponseModel {
    private String statusCode;
    private String data;

    // No-argument constructor
    public ResponseModel() {
    }

    // Parameterized constructor
    public ResponseModel(String statusCode, String data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    // Getter for statusCode
    public String getStatusCode() {
        return statusCode;
    }

    // Setter for statusCode
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    // Getter for data
    public String getData() {
        return data;
    }

    // Setter for data
    public void setData(String data) {
        this.data = data;
    }
}
