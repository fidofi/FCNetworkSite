package com.fido.fcnetworksite

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * @author: fido
 * @desription:
 * @date: Created in 17:11 2018/10/11
 */
@SpringBootApplication
@MapperScan(value = ["com.fido.fcnetworksite.dao"])
@EnableScheduling
open class FcnetworksiteApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(FcnetworksiteApplication::class.java, *args)
        }
    }
}
