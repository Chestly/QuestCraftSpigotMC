package net.questcraft.servercontact;



import java.io.IOException;

public class ServerContactUtil {
    ServerContactDAO serverContact = new ServerContactImplementer();
    static ServerContactUtil instance;
    public WebServerReturnData getVerification(String username) throws ContactError, IOException, ErrorClass {
        return serverContact.getVerification(username);
    }
    public ApplicationResponse getApp(String username) throws ErrorClass, IOException, ContactError {
        return serverContact.getApp(username);
    }
    public static synchronized ServerContactUtil getInstance() {
        if (instance == null) {
            instance = new ServerContactUtil();
        }
        return instance;
    }
}
