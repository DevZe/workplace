package Api;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

//ApiClient BaseClass
public class ApiClient {
    HttpClient client;
    protected HttpRequest request;
    protected HttpResponse<String> response;
    public String body="";
    protected int statusCode = 0;

    //Get method
    public int getAsync(String url) throws IOException, InterruptedException, NoSuchAlgorithmException {
        client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .executor(Executors.newFixedThreadPool(3))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .priority(2)
                .proxy(ProxySelector.getDefault())
                .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL))
                .sslContext(SSLContext.getDefault())
                .version(HttpClient.Version.HTTP_2)
                .sslParameters(new SSLParameters())
                .build();


        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format(url)))
                .header("Accept","application/json")
                .setHeader("User-Agent","ItekiTrial")

                .build();

        CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request,HttpResponse.BodyHandlers.ofString());
        completableFuture.thenApplyAsync(HttpResponse::headers)
                .thenAcceptAsync(System.out::println);
        response = completableFuture.join();


        return response.statusCode();
        // System.out.println("Body "+ response.body());
    }

    //this method gets the userinfo
    public int getUserInfor(String url,String token) throws NoSuchAlgorithmException {
        client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .executor(Executors.newFixedThreadPool(3))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .priority(2)
                .proxy(ProxySelector.getDefault())
                .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL))
                .sslContext(SSLContext.getDefault())
                .version(HttpClient.Version.HTTP_2)
                .sslParameters(new SSLParameters())
                .build();


        request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format(url)))
                .header("Accept","application/json")
                .setHeader("User-Agent","ItekiTrial")
                .setHeader("Authorization","bearer "+token)
                .build();

        CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request,HttpResponse.BodyHandlers.ofString());
        completableFuture.thenApplyAsync(HttpResponse::headers)
                .thenAcceptAsync(System.out::println);
        response = completableFuture.join();


        return response.statusCode();
    }

    //PostMethod
    public int postAsync(String url, Map<String, String> data) throws NoSuchAlgorithmException, IOException, InterruptedException {

        client = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .executor(Executors.newFixedThreadPool(3))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .priority(2)
                .proxy(ProxySelector.getDefault())
                .sslContext(SSLContext.getDefault())
                .version(HttpClient.Version.HTTP_2)
                .sslParameters(new SSLParameters())
                .build();

        request = HttpRequest.newBuilder()
                .POST(ofFormData(data))
                .uri(URI.create(String.format(url)))
                .header("content-Type","application/x-www-form-urlencoded")
                .setHeader("User-Agent","ItekiTrial")
                .build();

        response = client.send(request,HttpResponse.BodyHandlers.ofString());

        statusCode = response.statusCode();
        body = response.body();

        return statusCode;
    }

    //Delete method with delete uri as a parameter
    public int delete(String deleteUrl, Map<String,String> data) throws NoSuchAlgorithmException, IOException, InterruptedException {
        client = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .executor(Executors.newFixedThreadPool(3))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .priority(2)
                .proxy(ProxySelector.getDefault())
                .sslContext(SSLContext.getDefault())
                .version(HttpClient.Version.HTTP_2)
                .sslParameters(new SSLParameters())
                .build();

        request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(String.format(deleteUrl)))
                .header("content-Type","application/x-www-form-urlencoded")
                .setHeader("User-Agent","ItekiTrial")
                .build();

        response = client.send(request,HttpResponse.BodyHandlers.ofString());
        statusCode = response.statusCode();
        body = response.body();

        return statusCode;
    }

    //BodyPublisher method
    public HttpRequest.BodyPublisher ofFormData(Map<String,String> data){
        StringBuilder builder =new StringBuilder();
        for(Map.Entry<String,String> entry: data.entrySet()){
            if (builder.length() > 0){
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString()));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

}
