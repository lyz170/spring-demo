import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mytest.security.domain.common.model.SecurityDailyInfo;
import com.mytest.security.domain.common.utils.HttpURLConnectionUtils;
import org.bson.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/1.
 */
public class GetStockDataTest {

    public static void main(String[] args) throws IOException {

        System.out.println("[Time-1] " + LocalDateTime.now());
        String url = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sh000001&scale=240&ma=no&datalen=10000";

        String s = HttpURLConnectionUtils.doGet(url, null);

        List<SecurityDailyInfo> personList = new Gson().fromJson(s, new TypeToken<List<SecurityDailyInfo>>() {
        }.getType());

        System.out.println(personList.size());
        System.out.println("[Time-2] " + LocalDateTime.now());

        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("SECURITY");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("SECURITY_DAILY_INFO");
            System.out.println("Select collection successfully");
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            List<Document> documents = new ArrayList<>();
            personList.forEach(p -> {
                Document document = new Document("securityCode", "sh000001")
                        .append("day", p.getDay())
                        .append("open", p.getOpen())
                        .append("high", p.getHigh())
                        .append("low", p.getLow())
                        .append("close", p.getClose())
                        .append("volume", p.getVolume());
                documents.add(document);
            });
            collection.insertMany(documents);
            System.out.println("Insert data success.");
            System.out.println(documents.size());
            System.out.println("[Time-3] " + LocalDateTime.now());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
