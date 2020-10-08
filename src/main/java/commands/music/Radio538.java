package commands.music;

import commands.ICommand;
import functions.AllowedToPlayMusic;
import music.PlayerManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Radio538 implements ICommand {
    GuildMessageReceivedEvent e;

    String command = "radio538";
    String commandAlias = "radio538";
    String category = "music";
    String exampleCommand = "!radio538";
    String shortCommandDescription = "Listen to live radio!";
    String fullCommandDescription = "Listen to live radio!";

    @Override
    public void command(GuildMessageReceivedEvent event, String[] args) {
        e = event;

        AllowedToPlayMusic allowedToPlayMusic = new AllowedToPlayMusic();
        if (!allowedToPlayMusic.allowedToPlayMusic(e, args)) {
            return;
        }

        String url = "http://playerservices.streamtheworld.com/api/livestream-redirect/RADIO538.mp3";

        PlayerManager manager = PlayerManager.getInstance();

        manager.loadAndPlayRadio(e.getChannel(), url, e.getAuthor().getId(), e.getAuthor().getAsTag(), "Radio 538");

        e.getChannel().sendMessage("Test").queue();
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getCommandAlias() {
        return commandAlias;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getExampleCommand() {
        return exampleCommand;
    }

    @Override
    public String getShortCommandDescription() {
        return shortCommandDescription;
    }

    @Override
    public String getFullCommandDescription() {
        return fullCommandDescription;
    }
}