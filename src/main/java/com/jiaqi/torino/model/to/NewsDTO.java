package com.jiaqi.torino.model.to;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsDTO {

    private String source;

    private String url;

    private String title;

    private ZonedDateTime createTime;

    private ZonedDateTime publishTime;
}