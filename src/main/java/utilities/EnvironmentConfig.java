package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("file:environmentConfig/${server}.properties")
public interface EnvironmentConfig extends Config {

    @Key("App.Url")
    String appUrl();

    @Key("User.Email")
    String userEmail();

    @Key("User.Password")
    String userPassword();
}
