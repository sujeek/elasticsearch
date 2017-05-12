package es.documentApi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticSearchGet {

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

			GetResponse response = client.prepareGet("blog", "article", "1").get();
			GetResponse response1 = client.prepareGet("blog", "article", "1")
			        .setOperationThreaded(false)
			        .get();
			String res = response.getSourceAsString();
			System.out.print(res);
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
