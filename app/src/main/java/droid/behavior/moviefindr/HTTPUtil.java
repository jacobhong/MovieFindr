package droid.behavior.moviefindr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * Created by pairenoid on 3/21/2015.
 */
public class HTTPUtil {

        private String api = "http://api.themoviedb.org/3/search/movie?api_key=ebf4927497e3adc2502025f897c842d2&query=";
        private String json;

        public String getJSON(String url) {
            try {
                DefaultHttpClient client = new DefaultHttpClient();
                String encodedURL = api + URLEncoder.encode(url, "UTF-8");
                HttpGet getRequest = new HttpGet(encodedURL);
                HttpResponse getResponse = client.execute(getRequest);
                int statusCode = getResponse.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    return null;
                }
                HttpEntity entity = getResponse.getEntity();
                json = EntityUtils.toString(entity);
                return json;
            } catch (ClientProtocolException e) {
            } catch (IOException e) {

            }
            return null;
        }

        public InputStream getIs(String url) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(url);
            InputStream is;
            try {
                HttpResponse getResponse = client.execute(getRequest);
                int statusCode = getResponse.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    return null;
                }
                is = getResponse.getEntity().getContent();
                return is;
            } catch (ClientProtocolException e) {
            } catch (IOException e) {
            }

            return null;

        }

        public Bitmap getBitmap(String url) {
            InputStream is;
            is = this.getIs(url);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
                return bitmap;
            } catch (Exception e) {
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                    }
                }

            }
            return null;
        }
}
