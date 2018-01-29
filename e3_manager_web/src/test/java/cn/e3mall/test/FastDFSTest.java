package cn.e3mall.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.utils.FastDFSClient;

public class FastDFSTest {
	@Test
	public void testUpload() throws FileNotFoundException, IOException, MyException{
		//创建一个配置文件,配置trackerServer的ip地址和端口号
		//加载配置文件
		ClientGlobal.init("F:/software/Eclipse/workspaceProject02_E3Mall/e3_manager_web/src/main/resources/config/client.conf");
		//创建一个TrackerClient
		TrackerClient trackerClient = new TrackerClient();
		//获取TrackerServer
		TrackerServer trackerServer = trackerClient.getConnection();
		//创建StorageClient
		StorageClient storageClient = new StorageClient(trackerServer,null);
		//参数1: 本地文件路径及文件名; 参数2:扩展名,不带.;参数3:元数据
		String[] str = storageClient.upload_file("G:/照片/思思高温假/美/IMG_0602.JPG", "JPG", null);
		for (String s : str) {
			System.out.println(s);
		}
	}
	
	@Test
	public void testFastFDSClient() throws Exception{
		FastDFSClient client =new FastDFSClient("F:/software/Eclipse/workspaceProject02_E3Mall/e3_manager_web/src/main/resources/config/client.conf");
		String url = client.uploadFile("G:/照片/思思高温假/美/IMG_0602.JPG", "JPG", null);
		System.out.println(url);
	}
}
