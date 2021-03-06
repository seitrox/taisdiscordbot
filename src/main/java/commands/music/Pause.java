package commands.music;

import commands.CommandReceivedEvent;
import commands.ICommand;
import music.PlayerManager;

import static util.AllowedToPlayMusic.allowedToPlayMusic;

public class Pause implements ICommand {
    String command = "pause";
    String commandAlias = "pause";
    String category = "music";
    String exampleCommand = "`!pause`";
    String shortCommandDescription = "Pause the music.";
    String fullCommandDescription = "Pause the currently playing music.";

    @Override
    public void command(CommandReceivedEvent event) {

        if (!allowedToPlayMusic(event, "pause")) return;

        PlayerManager manager = PlayerManager.getInstance();

        manager.pause(event.getGuild());
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
