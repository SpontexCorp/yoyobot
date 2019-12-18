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
        if (content.equalsIgnoreCase("y.invite")) {
            event.getChannel().sendMessage("You can invite the bot by using the following url: https://discordapp.com/oauth2/authorize?client_id=480472135207223309&scope=bot&permissions=8");
        }
        if (content.equalsIgnoreCase("reactSmile")) {
            message.addReaction("😃");
        }
        if (content.equalsIgnoreCase("Franklin the")) {
            event.getChannel().sendMessage(":turtle:");
        }
        if (content.equalsIgnoreCase("y.wacky-sent")) {
            event.getChannel().sendMessage(e.randWackSent());
        }
        if (content.equalsIgnoreCase("y.sent")) {
            event.getChannel().sendMessage(e.randSent());
        }
        if (content.equalsIgnoreCase("y.yt")) {
            event.getChannel().sendMessage(e.randYT());
        }
        if (content.equalsIgnoreCase("y.help")) {
            event.getChannel().sendMessage("List of commands coming soon!");
        }
        if (content.equalsIgnoreCase("y.art1")) {
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Zen")
                    .setDescription("This piece of abstract art perfectly portrays the idea of zen, despite the chaotic surroundings. No doubt this five minute paint project will be worth a million dollars.")
                    .setAuthor("Yin4TheWin", null, "https://cdn.discordapp.com/emojis/478590372390633473.png?v=1")
                    .addField("Estimated Value", "USD 420.69 million")
                    .addInlineField("Messy Lines and BG (Meaning)", "Sometimes I even confuse myself.")
                    .addInlineField("Singular Dot (Meaning)", "The embodiment of zen itself, representing human endurance in tough times, yada yada.")
                    .setColor(Color.BLUE)
                    .setFooter("Copyrighted by me jk", "https://cdn.discordapp.com/emojis/478590372390633473.png?v=1")
                    .setImage(new File("C:\\Users\\Franklin\\Desktop\\Zen.png"))
                    .setThumbnail(new File("C:\\Users\\Franklin\\Desktop\\Zen.png"));
            event.getChannel().sendMessage(embed);
        }
        if (content.length()>6&&content.substring(0, 6).equals("y.ban ")) {
            event.deleteMessage();
            event.getChannel().sendMessage("Blacklist updated! To view blacklist use the \"y.list\" command.");
            blacklist.add(content.substring(6));
        }
        if(content.equalsIgnoreCase("I solemnly swear that I'm up to no good"))
        {
            String str = "сheese";
            int codePointAt0 = Character.codePointAt(str, 0);
            event.getChannel().sendMessage("*cue magic flashes* "+codePointAt0);
        }
        if(content.equalsIgnoreCase("Thanks Yin Bot"))
        {
            message.addReaction("😃");
            event.getChannel().sendMessage("Any time!");
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
