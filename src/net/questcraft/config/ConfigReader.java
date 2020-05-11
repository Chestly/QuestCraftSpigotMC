package net.questcraft.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class ConfigReader {
    private static ConfigReader instance;

    public String getProp(String property) throws IOException {

        Properties prop = getInput();
        String returnProp = prop.getProperty(property);
        return returnProp;
    }

    public Properties getInput() throws IOException {
        Properties prop = new Properties();
        FileInputStream input = null;
        try {
            String currentDir = System.getProperty("user.dir");
            Path folderPath = Paths.get(currentDir + "/QuestCraftSPIGOT");
            File folderFile = new File(folderPath + "/QuestCraftSPIGOT.yml");
            Path filePath = Paths.get(folderPath + "/QuestCraftSPIGOT.yml");
            if (!Files.exists(filePath)) {

                this.initiate();
            }
            input = new FileInputStream(folderFile);

            prop.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return prop;
    }

    public void initiate() {
        try {
            String currentDir = System.getProperty("user.dir");
            File folderFile = new File(currentDir + "/QuestCraftSPIGOT");
            folderFile.mkdirs();
            FileOutputStream output = new FileOutputStream(folderFile + "/QuestCraftSPIGOT.yml");

            Properties prop = new Properties();
            prop.setProperty("admins", "");
            prop.store(output, null);


        } catch (IOException ex) {
        }
    }

    public void storeProp(String name, String property) throws IOException {
        System.out.println("storin prop");

        FileOutputStream output = null;
        try {
            String currentDir = System.getProperty("user.dir");
            System.out.println(currentDir);
            Path folderPath = Paths.get(currentDir + "/QuestCraftSPIGOT");
            System.out.println(folderPath);
            File folderFile = new File(folderPath + "/QuestCraftSPIGOT.yml");
            System.out.println(folderFile);

            Properties prop = this.getInput();
            output = new FileOutputStream(folderFile);

            prop.setProperty(name, property);
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }
}
