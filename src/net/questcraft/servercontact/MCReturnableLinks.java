package net.questcraft.servercontact;


public class MCReturnableLinks implements WebServerReturnData {
    String account;
    String application;
    String accountUser;
    String appUser;

    public String getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(String accountUser) {
        this.accountUser = accountUser;
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}
