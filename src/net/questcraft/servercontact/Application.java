package net.questcraft.servercontact;



public class Application  {
    String questions;
    String mcUsername;
    String discordUsername;
    String email;
    String questCraftAccount;
    String pendingMCUser;
    String pendingEmail;
    String emailVerifyCode;
    String pendingDiscordUser;
    Integer status;
    String discordVerifyCode;
    String minecraftVerifyCode;
    Integer id;

    public Application(String questions, String mcUsername, String discordUsername, String email, String questCraftAccount, String pendingMC, String pendingEmail, String emailVerifyCode, String pendingDiscord, Integer status, String minecraftVerifyCode, String discordVerifyCode, Integer id) {
        this.questions = questions;
        this.mcUsername = mcUsername;
        this.discordUsername = discordUsername;
        this.email = email;
        this.questCraftAccount = questCraftAccount;
        this.pendingMCUser = pendingMC;
        this.pendingEmail = pendingEmail;
        this.emailVerifyCode = emailVerifyCode;
        this.pendingDiscordUser = pendingDiscord;
        this.status = status;
        this.minecraftVerifyCode = minecraftVerifyCode;
        this.discordVerifyCode = discordVerifyCode;
        this.id = id;
    }


    public Integer getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDiscordVerifyCode() {
        return discordVerifyCode;
    }

    public void setDiscordVerifyCode(String discordVerifyCode) {
        this.discordVerifyCode = discordVerifyCode;
    }

    public String getMinecraftVerifyCode() {
        return minecraftVerifyCode;
    }

    public void setMinecraftVerifyCode(String minecraftVerifyCode) {
        this.minecraftVerifyCode = minecraftVerifyCode;
    }

    public String getPendingMCUser() {
        return pendingMCUser;
    }

    public void setPendingMCUser(String pendingMCUser) {
        this.pendingMCUser = pendingMCUser;
    }

    public String getPendingEmail() {
        return pendingEmail;
    }

    public void setPendingEmail(String pendingEmail) {
        this.pendingEmail = pendingEmail;
    }

    public String getEmailVerifyCode() {
        return emailVerifyCode;
    }

    public void setEmailVerifyCode(String emailVerifyCode) {
        this.emailVerifyCode = emailVerifyCode;
    }

    public String getPendingDiscordUser() {
        return pendingDiscordUser;
    }

    public void setPendingDiscordUser(String pendingDiscordUser) {
        this.pendingDiscordUser = pendingDiscordUser;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getMcUsername() {
        return mcUsername;
    }

    public void setMcUsername(String mcUsername) {
        this.mcUsername = mcUsername;
    }

    public String getDiscordUsername() {
        return discordUsername;
    }

    public void setDiscordUsername(String discordUsername) {
        this.discordUsername = discordUsername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuestCraftAccount() {
        return questCraftAccount;
    }

    public void setQuestCraftAccount(String questCraftAccount) {
        this.questCraftAccount = questCraftAccount;
    }

    public Application(String questions, String mcUsername, String discordUsername, String email, String questCraftAccount, Integer status) {
        this.questions = questions;
        this.mcUsername = mcUsername;
        this.discordUsername = discordUsername;
        this.email = email;
        this.questCraftAccount = questCraftAccount;
        this.status = status;
    }
    public Application() {
    }
}
