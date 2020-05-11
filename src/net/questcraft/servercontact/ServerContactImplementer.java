package net.questcraft.servercontact;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE;

public class ServerContactImplementer implements ServerContactDAO {
    private static final String HOST = "localhost:4567";

    @Override
    public MCReturnableLinks getVerification(String user) throws ContactError, IOException, ErrorClass {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(WRAP_ROOT_VALUE, true);
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        URL obj = new URL("http://" + HOST + "/getMCVerification?mcUser=" + URLEncoder.encode(user, "UTF-8"));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        System.out.println(con.getResponseCode());
        return (MCReturnableLinks) nodeResponseHandler(objectMapper, con);


    }

    @Override
    public ApplicationResponse getApp(String user) throws ContactError, ErrorClass, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(WRAP_ROOT_VALUE, true);
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        URL obj = new URL("http://" + HOST + "/getApplicationData?username=" + URLEncoder.encode(user, "UTF-8"));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        System.out.println(con.getResponseCode());
        return (ApplicationResponse) nodeResponseHandler(objectMapper, con);


    }

    public WebServerReturnData nodeResponseHandler(ObjectMapper objectMapper, HttpURLConnection con) throws IOException, ContactError, ErrorClass {
        StringBuilder content;

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;
        content = new StringBuilder();

        while ((line = in.readLine()) != null) {

            content.append(line);
            content.append(System.lineSeparator());
        }
        System.out.println(content);

        if (content.toString().contains("ErrorClass")) {

            ErrorClass error = objectMapper.readValue(content.toString(), ErrorClass.class);
            throw error;
        } else if (content.toString().contains("MCReturnableLinks")) {
            MCReturnableLinks app = objectMapper.readValue(content.toString(), MCReturnableLinks.class);
            return app;
        } else if (content.toString().contains("Application")) {
            ApplicationResponse applicationResponse = new ApplicationResponse();
            applicationResponse.setApplication(objectMapper.readValue(content.toString(), Application.class));
            applicationResponse.setStatus(200);
            return applicationResponse;
        } else {
            throw new ContactError("Unidentified JSON return", 2);
        }


    }
}
