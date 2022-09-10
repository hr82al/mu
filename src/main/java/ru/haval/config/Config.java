package ru.haval.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Map;
import java.util.Properties;
import ru.haval.application.Main;

public class Config {
    private boolean isValid;
    private static String confFileName;
    private static volatile Config instance;
    private String user;
    private String password;
    private int language;
    private String address;
    private int port;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private String dbParams;
    private Properties props;


    private Config(){
        isValid = false;
        Map<String, String> env = System.getenv();
        confFileName = env.get("APPDATA");
        if (confFileName == null) {
            confFileName = env.get("HOME");
        }
        confFileName = confFileName  + "/config.xml";
        File file = new File(confFileName);
        if (!file.exists()) {
            try {
                File sourceFile = new File(Main.jarLocation + "/config.xml");
                if (sourceFile.exists()) {
                    Path source = Paths.get(Main.jarLocation + "/config.xml");
                    Path target = Paths.get(confFileName);

                    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

                } else {
                    new FileOutputStream(confFileName).close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadState();
        //Save parameters after close the application
        Runtime.getRuntime().addShutdownHook(new Thread(this::saveState));

    }

    public static Config getInstance() {
        Config localInstance = instance;
        if (localInstance == null) {
            synchronized (Config.class) {
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new Config();
                }
            }
        }
        return localInstance;
    }

    private void loadState() {
        File confFile = new File(confFileName);
        try {
            InputStream inputStream = new FileInputStream(confFile);
            props = new Properties();
            props.loadFromXML(inputStream);
            user = props.getProperty("user");
            language = Integer.parseInt(props.getProperty("language"));
            address = props.getProperty("address");
            port = Integer.parseInt(props.getProperty("port"));
            dbName = props.getProperty("db.name");
            dbUser = props.getProperty("db.user");
            dbParams = props.getProperty("db.params");

            //Декодирование паролей
            password = new String(Base64.getDecoder().decode(props.getProperty("password")));
            dbPassword = new String(Base64.getDecoder().decode(props.getProperty("DBPassword")));
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void saveState() {
        if (!isValid) return;
        try {
            File configFile = new File(confFileName);
            OutputStream outputStream = new FileOutputStream(configFile);
            props.setProperty("user", user);
            props.setProperty("language", String.valueOf(language));
            props.setProperty("address", address);
            props.setProperty("port", String.valueOf(port));
            props.setProperty("db.name", dbName);
            props.setProperty("db.user", dbUser);
            props.setProperty("db.params", dbParams);

            //Кодирование пароля
            props.setProperty("password", Base64.getEncoder().encodeToString(password.getBytes()));
            props.setProperty("DBPassword", Base64.getEncoder().encodeToString(dbPassword.getBytes()));
            props.storeToXML(outputStream, "MU configuration file.");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;

    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getPort() {
        return port;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbParams() {
        return dbParams;
    }

    public void setDbParams(String dbParams) {
        this.dbParams = dbParams;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}
