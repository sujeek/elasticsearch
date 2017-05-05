package es;

import java.net.InetAddress;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class esClient {

	
	public static void main(String[] args) {
		try {
		

            //设置集群名称
            //Settings settings1 = Settings.builder().put("cluster.name", "my-elasticsearch")
            									//.put("client.transport.sniff", true).build();
            //创建client
           
			TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.224.140"), 9300));
            	
            //搜索数据
            GetResponse response = client.prepareGet("blog", "article", "1").execute().actionGet();
            //输出结果
            System.out.println(response.getSourceAsString());
            //关闭client
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


	}

}
