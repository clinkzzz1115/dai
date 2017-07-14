package com.will.project.dai.entity;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * created by jiayi.chen on 2017/7/6
 */
@Builder
@Data
public class Sheet<T> {

  private long rowCount;

  private List<T> data;

}
