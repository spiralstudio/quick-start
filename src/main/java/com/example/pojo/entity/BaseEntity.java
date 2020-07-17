package com.example.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    /** id */
    protected Long id;
    /** create time */
    protected LocalDateTime createTime;
    /** update time */
    protected LocalDateTime modifiedTime;

}
