package net.questcraft;

import net.questcraft.servercontact.ContactError;
import net.questcraft.servercontact.ServerContactUtil;
import net.questcraft.servercontact.ErrorClass;
import org.junit.Test;

import java.io.IOException;

public class ServerContactTests {
    @Test
    public void testGetApp() throws IOException, ContactError, ErrorClass {
        ServerContactUtil serverContactUtil = ServerContactUtil.getInstance();
        System.out.println(serverContactUtil.getApp("Chestly").getApplication().getStatus());
    }
}
