package com.fido.fcnetworksite.config;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/30 22:57
 * @description:
 */
@Configuration
public class PageHelperConfig {
  @Bean
  public PageHelper pageHelper() {
    PageHelper pageHelper = new PageHelper();
    Properties p = new Properties();
    p.setProperty("offsetAsPageNum", "true");
    p.setProperty("rowBoundsWithCount", "true");
    p.setProperty("reasonable", "true");
    // 通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。
    p.setProperty("pageSizeZero", "true");
    pageHelper.setProperties(p);
    return pageHelper;
  }
}
