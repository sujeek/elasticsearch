package es;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import java.io.IOException;
import java.util.Date;
import org.elasticsearch.common.xcontent.XContentBuilder;

public class getJson {

	public static void main(String[] args) {
		XContentBuilder builder;
		String json = "";
		try {
			 builder = jsonBuilder().startObject()
					.field("user", "kimchy")
					.field("postDate", new Date())
					.field("message", "trying out Elasticsearch")
					.endObject();
			 json = builder.string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 System.out.print(json);

	}

}
