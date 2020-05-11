package net.questcraft;

import net.questcraft.servercontact.ContactError;
import net.questcraft.servercontact.ErrorClass;
import net.questcraft.servercontact.MCReturnableLinks;
import net.questcraft.servercontact.ServerContactUtil;
import org.junit.Test;

import java.io.IOException;

public class VerificationTests {
    @Test
    public void testAppVerify() throws ErrorClass, IOException, ContactError {
        ServerContactUtil contactUtil = ServerContactUtil.getInstance();
        MCReturnableLinks mcReturnableLinks = (MCReturnableLinks) contactUtil.getVerification("Chestly");
        System.out.println(mcReturnableLinks.getApplication());
    }
}
