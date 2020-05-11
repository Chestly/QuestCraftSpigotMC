package net.questcraft.servercontact;

public class ContactError extends Throwable {
    public ContactError(String message, Integer code) {
        this.message = message;
        this.errorCode = code;

        //Error Codes
        //JsonMappingException: 1
        //Unidentified JSON return: 2
    }
    String message;
    Integer errorCode;
    //SQLException: 1
    //AccountException: 2
    //Cant FInd uuid in list: 3
    //js Response is null: 4
    //incorrect password: 5
    //incorrect email Verification: 6
    //Email Failed to Send: 7
    //IOEXpetion(discord send failed): 8
    //Incorrect Server Pin: 9

    //NODE ERROR CODES:
    //10: couldnt find user in server
    //12 couldnt contact discord BOt: 12

    //General Errors:
    //Unidentied Error: 11

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
