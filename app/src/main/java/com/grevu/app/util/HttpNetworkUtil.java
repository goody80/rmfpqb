package com.grevu.app.util;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Created by jason on 14. 11. 6..
 */
public class HttpNetworkUtil {
    private static final int HTTP_TIMEOUT = 5000;

    /**
     * HTTP Post
     * */
    public static HttpResult sendHttpPost(String url) {

        HttpResult result = new HttpResult();

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, HTTP_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, HTTP_TIMEOUT);
        HttpPost request = new HttpPost(url);

        HttpClient client = new DefaultHttpClient();

        try {
            HttpResponse httpResponse = client.execute(request);
            HttpEntity httpEntity = httpResponse.getEntity();

            int status = httpResponse.getStatusLine().getStatusCode();

            result.setStatus(status);
        } catch (ClientProtocolException cpe) {

        } catch (ConnectTimeoutException cte) {

        } catch (Exception e) {

        } finally {

        }
        return result;
    }
}
