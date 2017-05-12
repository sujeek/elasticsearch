package es.documentApi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticSearchDeleteByQuery {

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
			BulkByScrollResponse response =
				    DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
				        .filter(QueryBuilders.matchQuery("gender", "male")) 
				        .source("persons")                                  
				        .get();                                             

			long deleted = response.getDeleted();
			System.out.print(deleted);
						
			
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
