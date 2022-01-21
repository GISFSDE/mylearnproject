package pers.lxl.mylearnproject.programbase.nosql.mongolearn;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
public class HelloMongoDB {
//1.添加依赖
//            <dependency>
//            <groupId>org.mongodb</groupId>
//            <artifactId>mongodb-driver-sync</artifactId>
//            <version>4.4.1</version>
//        </dependency>

//    2.连接到数据库
public static void main( String args[] ){
//    try{
//        // 连接到 mongodb 服务
//        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//
//        // 连接到数据库
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
//        System.out.println("Connect to database successfully");
//
//    }catch(Exception e){
//        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//    }

// Replace the uri string with your MongoDB deployment's connection string
//    IllegalArgumentException: Unsupported compressor 'disabled'
//    String uri = "mongodb://127.0.0.1:27017/?compressors=disabled&gssapiServiceName=mongodb";
//    Exception in thread "main" java.lang.NoClassDefFoundError: com/mongodb/internal/connection/InternalConnectionPoolSettings  升级springboot版本
    String uri = "mongodb://127.0.0.1:27017/?gssapiServiceName=mongodb";
    try (MongoClient mongoClient = MongoClients.create(uri)) {
//        show dbs
        MongoDatabase database = mongoClient.getDatabase("student");
//        show collections 或 show tables
        MongoCollection<Document> collection = database.getCollection("student");
        Document doc = collection.find(eq("age", 11)).first();
        System.out.println(doc.toJson()+"==========");
    }

}

}

