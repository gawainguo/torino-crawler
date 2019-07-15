package com.jiaqi.torino.model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Response {
    private boolean success;

    private String msg;

    private Object data;

    public static Response success() {
        return Response.builder().success(true).build();
    }

    public static Response error() {
        return Response.builder().success(false).build();
    }

    public Response withData(Object data) {
        this.data = data;
        return this;
    }

    public Response withMsg(String msg) {
        this.msg = msg;
        return this;
    }
}