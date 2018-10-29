package com.fido.fcnetworksite.dao;

import com.fido.fcnetworksite.AbstractUnitTest;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/27 23:01
 * @description:
 */
public class FtpTest extends AbstractUnitTest {
  @Test
  public void testFtpClient() throws Exception {
    // 1. 创建一个FtpClient对象
    FTPClient ftpClient = new FTPClient();
    // 2. 创建 ftp 连接
    ftpClient.connect("47.106.199.194", 21);
    // 3. 登录 ftp 服务器
    ftpClient.login("ftpuser", "ftpuser");
    // 4. 读取本地文件
    FileInputStream inputStream = new FileInputStream(new File("E://test.jpg"));
    // 5. 设置上传的路径
    ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
    // 6. 修改上传文件的格式为二进制
    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    // 7. 服务器存储文件，第一个参数是存储在服务器的文件名，第二个参数是文件流
    ftpClient.enterLocalPassiveMode();
    System.out.println(ftpClient.storeFile("test.jpg", inputStream));
    // 8. 关闭连接
    ftpClient.logout();
  }
}
