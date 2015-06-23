package broconut.ciangallagher.net;

/**
 * Created by Cian on 20/06/2015.
 */

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Process.class
 * Processes image locally and uploads it
 * to specified server externally.
 * TODO: Add functionality to allow user to specify upload an store location
 */


class Process {

    // Local Variables
    private String localPath;
    private String url;
    private String resURL;
    private String postParam = "imagedata";

    // Objects
    private Errors error;

    public Process () {
        error = new Errors();
    }

    public void ProcessImage () {

        // attempt to read from properties
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // load the file
            prop.load(input);

            // get the property value
            this.url        = prop.getProperty("URL");
            this.localPath  = prop.getProperty("PATH");

            if (url.isEmpty() || url == null || localPath.isEmpty() || localPath == null) {
                // do something
            } else {
                // do something else
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {e.printStackTrace();}
            }
        }

    }

    // TODO: Save image locally
    public void SaveImage () {
        System.out.println("Image Saved.");
    }

    public void UploadImage (File file) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpPost post = new HttpPost(this.url);

            FileBody fBody = new FileBody(file);

            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart(this.postParam, fBody)
                    .build();

            post.setEntity(reqEntity);

            CloseableHttpResponse response = httpclient.execute(post);
            try {
                // get response if needed
                HttpEntity resEntity = response.getEntity();
                this.resURL = EntityUtils.toString(resEntity);
            } finally {
                response.close();
            }

        } catch(Exception e) {
            // TODO: generate error dialog
            e.printStackTrace();
        } finally {
            httpclient.close();
        }

        // finally, open new browser window with image
        if (Desktop.isDesktopSupported()) {
            try {
                if (!resURL.isEmpty()) {
                    Desktop.getDesktop().browse(new URI(resURL));
                } else {
                    // TODO: generate error dialog
                }
            } catch (URISyntaxException|IOException e) {e.printStackTrace();}
        }

    }

    public void SetLocalPath (String path) {this.localPath = path;}
    public String GetLocalPath () {return this.localPath;}

    public void SetURL (String url) {this.url = url;}
    public String GetURL () {return this.url;}
}
