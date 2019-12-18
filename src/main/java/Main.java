import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main extends ListenerAdapter {
    private static String filename = "/Users/Vinz/IdeaProjects/yoyobot/src/main/resources/yoda.txt";

    public static void main(String[] args) throws LoginException, FileNotFoundException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NjU2OTcwMjUyOTk5MDY1NjQz.XfqZUg.WvfHqNke8FmuSlttngAme1S2fTQ";
        builder.setToken(token);

        builder.addEventListeners(new Main());
        builder.build();

    }

    private static String generateYodaSentence() {
        List<String> text = readFile(filename);
        String[] textList = text.get(0).split(",");
        int Random1 =(int)(Math.random()*(552));
        int Random2 =(int)(Math.random()*(552));
        int Random3 =(int)(Math.random()*(552));

        return (textList[Random1] + "," + textList[Random2] + "," + textList[Random3]);
    }

    private static String generateWTFYodaSentence() {
        List<String> text = readFile(filename);
        String[] textList = text.get(0).split(" ");
        String Sentence = null;
        for(int i = 0; i < 30; i++){
            int Random =(int)(Math.random()*(6932));
            Sentence = Sentence + " " + textList[Random];
        }

        return Sentence;
    }

    private static List<String> readFile(String filename)
    {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }

    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("Message reçu de " + event.getAuthor().getName() + " : " + event.getMessage().getContentDisplay());

        if(event.getMessage().getContentRaw().equals("yoyo")) {
            event.getChannel().sendMessage(generateYodaSentence()).queue();
        }
        else if(event.getMessage().getContentRaw().equals("yoyo wtf")) {
            event.getChannel().sendMessage(generateWTFYodaSentence()).queue();
        }
    }
}
