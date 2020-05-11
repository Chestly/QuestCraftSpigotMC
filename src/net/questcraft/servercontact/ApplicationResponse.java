package net.questcraft.servercontact;

public class ApplicationResponse implements WebServerReturnData {
    public ApplicationResponse() {
    }

    public Integer getStatus() {
        //Status Codes:
        //1: OK
        //2: Internal Error
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Application getApplication() {
        //Status Codes:
        //1: OK
        //2: Internal Error
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
    Application application;
    Integer status;

}
