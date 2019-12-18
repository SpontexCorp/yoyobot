import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.util.logging.ExceptionLogger;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class BotListener extends Main implements MessageCreateListener {
    ArrayList<String> blacklist = new ArrayList<>();

    ArrayList<Integer> confirm = new ArrayList<>();

    Extension e=new Extension();

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();
        String content = message.getContent();
        if(content.equalsIgnoreCase("y.source"))
        {
            event.getChannel().sendMessage();
        }
        if(confirm.size()!=0&&confirm.get(confirm.size()-1)==1)
        {
            if(content.equalsIgnoreCase("confirm")) {
                if (blacklist.size() != 0) {
                    for (int x = 0; x < blacklist.size(); x++)
                        //event.getChannel().sendMessage(blacklist.get(x) + "\n");
                        event.getChannel().sendMessage("This is supposed to be a blacklisted word \n");
                } else
                    event.getChannel().sendMessage("There are no words in the blacklist!");
                confirm.add(0);
            }
        }
        if (content.equalsIgnoreCase("y.list"))
        {
            event.getChannel().sendMessage("Are you sure? Your entire blacklist will be displayed! It is advised that you go into a private channel to do this. If you are sure, please type \"confirm\".");
            confirm.add(1);
        }
        for(int x=0;x<blacklist.size();x++)
        {
            if(content.indexOf(blacklist.get(x))>-1)
                event.deleteMessage();
        }
        int delete=0;
        for (int x = 0; x < content.length(); x++) {
            if (delete == 0 && content.toUpperCase().charAt(x) == 'N')
                delete++;
            else if ((delete == 2 || delete == 3) && (content.toUpperCase().charAt(x) == 'B'||content.toUpperCase().charAt(x) == 'G'))
                delete++;
            else if (delete == 4 && (content.toUpperCase().charAt(x) == 'A'||content.toUpperCase().charAt(x) == 'E'))
                delete++;
            else if (delete == 1 && (content.toUpperCase().charAt(x) == 'I' || content.toUpperCase().charAt(x) == 'l'|| content.toUpperCase().charAt(x) == '*'))
                delete++;
            else if (delete == 5 && content.toUpperCase().charAt(x) == 'R')
                delete++;
            else if (content.toUpperCase().charAt(x) >= 65 & content.toUpperCase().charAt(x) <= 90)
                delete = 0;
            if (delete == 5||delete==6) {
                message
                        .getUserAuthor()
                        .ifPresent(user -> event
                                .getChannel()
                                .sendMessage("<@366320583807598593>! Someone's been using racial slurs! No names but..." + user.getMentionTag())
                                .exceptionally(ExceptionLogger.get()));
                event.deleteMessage("Bad boi");
                x = 5000;
            }
        }
        delete=0;
        for (int x = 0; x < content.length(); x++) {
            if (delete == 0 && (content.toUpperCase().charAt(x) == 'C'||content.toUpperCase().charAt(x) == 155||content.toUpperCase().charAt(x) == 128))
                delete++;
            else if ((delete == 2 || delete == 3|| delete == 5) && content.toUpperCase().charAt(x) == 'E'||content.toUpperCase().charAt(x) == 130||content.toUpperCase().charAt(x) ==136||content.toUpperCase().charAt(x) == 137||content.toUpperCase().charAt(x) == 138||content.toUpperCase().charAt(x) == 144)
                delete++;
            else if (delete == 1 && content.toUpperCase().charAt(x) == 'H')
                delete++;
            else if (delete == 4 && (content.toUpperCase().charAt(x) == 'S'))
                delete++;
            else if (content.toUpperCase().charAt(x) >= 65 & content.toUpperCase().charAt(x) <= 90)
                delete = 0;
            if (delete == 6) {
                message
                        .getUserAuthor()
                        .ifPresent(user -> event
                                .getChannel()
                                .sendMessage("Don't say that, " + user.getMentionTag()+"!")
                                .exceptionally(ExceptionLogger.get()));
                event.deleteMessage("Bad boi");
                x = 5000;
            }
        }
        if (content.equalsIgnoreCase("ping")) {
            event.getChannel().sendMessage("Pong!");
        }
        if (content.equalsIgnoreCase("pong")) {
            message
                    .getUserAuthor()
                    .ifPresent(user -> event
                            .getChannel()
                            .sendMessage("Ping! " + user.getMentionTag()));
        }
        if (content.equalsIgnoreCase("y.help")) {
            event.getChannel().sendMessage("Tu crois que c'est du respect ça mon garçon ?");
        }
        for(int i = 0; i <content.length(); i++) {
            if(Character.UnicodeBlock.of(content.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                event.deleteMessage();
                message
                        .getUserAuthor()
                        .ifPresent(user -> event
                                .getChannel()
                                .sendMessage("Don't use Cyrillic please ty " + user.getMentionTag()));
            }
        }
    }
}
