

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.swing.text.Document;
import javax.swing.text.Element;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {

        String a = "21730-13";
        String b = "291";
        String POST_API_URI ="https://fgis.gost.ru/fundmetrology/cm/xcdb/vri/select?fq=mi.mitnumber:"+ a + "&fq=mi.number:"+ b + "&q=*&fl=vri_id,org_title,mi.mitnumber,mi.mitype,mi.number,verification_date,valid_date,applicability&sort=verification_date+desc,org_title+asc&rows=10&start=0";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POST_API_URI))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        ObjectMapper mapper = objectMapper();
        JsonNode json = mapper.readTree(response.body());
        JsonNode docsArray = json.get("response").get("docs");
        CollectionLikeType postCollection = mapper.getTypeFactory().constructCollectionLikeType(ArrayList.class, Post.class);
        List<Post> posts = mapper.convertValue(docsArray, postCollection);

    }

    private static ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        return mapper;
    }

}
