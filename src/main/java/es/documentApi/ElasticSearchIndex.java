package es.documentApi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticSearchIndex {
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

			List<String> jsonData = DataFactory.getInitJsonData();

			for (int i = 0; i < jsonData.size(); i++) {
				IndexResponse response = client.prepareIndex("blog", "article").setSource(jsonData.get(i)).get();

				String _index = response.getIndex();
				// Type name
				String _type = response.getType();
				// Document ID (generated or not)
				String _id = response.getId();
				// Version (if it's the first time you index this document, you will get: 1)
				long _version = response.getVersion();
				// status has stored current instance statement.
				RestStatus status = response.status();
				
				System.out.println(_index);
				System.out.println(_type);
				System.out.println(_id);
				System.out.println(_version);
				System.out.println(status);
				

			}
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}