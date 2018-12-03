package com.sw.sgtu.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @SerializedName("valid")
    private int valid;

    @SerializedName("usuario")
    private List<UserResponse> userResponse;

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public List<UserResponse> getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(List<UserResponse> userResponse) {
        this.userResponse = userResponse;
    }
}
