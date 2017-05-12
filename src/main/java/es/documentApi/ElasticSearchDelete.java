package es.documentApi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticSearchDelete {

	public static void main(String[] args) {
		try {
			// 设置集群名称
			Settings settings = Settings.builder().put("cluster.name", "my-elasticsearch")
					.put("client.transport.sniff", true).build();
			// 创建client
			TransportClient client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.224.140"), 9300))
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.224.141"), 9300))
					.addTransportAddress(
							new InetSocketTransportAddress(InetAddress.getByName("192.168.224.142"), 9300));
			DeleteResponse response = client.prepareDelete("blog", "article", "AVvYUN6QAl9waYY55JSH").get();
			/*DeleteResponse response1 = client.prepareDelete("blog", "article", "1")
			        .setOperationThreaded(false)
			        .get();*/
			
			Result res = response.getResult();
			System.out.print(res);
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
