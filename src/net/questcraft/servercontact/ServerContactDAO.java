package net.questcraft.servercontact;

import java.io.IOException;

public interface ServerContactDAO {
    MCReturnableLinks getVerification(String user) throws ContactError, IOException, ErrorClass;
    ApplicationResponse getApp(String user) throws ContactError, ErrorClass, IOException;
}
