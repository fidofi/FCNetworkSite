package com.fido.fcnetworksite

import org.apache.ibatis.session.SqlSessionFactory
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author: fido
 * @desription:
 * @date: Created in 10:10 2018/10/12
 */
@RunWith(SpringRunner::class)
@SpringBootTest
abstract class AbstractUnitTest {
//    @Autowired
//    lateinit var sessionFactory: SqlSessionFactory
//
//    protected fun cleanUpDb(sql: String) {
//        sessionFactory.openSession().connection.prepareStatement(sql).execute()
//    }
}