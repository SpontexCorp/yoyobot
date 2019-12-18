import org.javacord.api.*;

public class Main{
    public static void main(String[] args) {
        String token = "nOnE oF yOuR bEesWAx";

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        api.setMessageCacheSize(10, 60 * 60);

        api.addListener(new BotListener());

    }
}
