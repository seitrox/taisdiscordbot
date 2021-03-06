package commands.music;

import commands.CommandEnum;
import commands.CommandReceivedEvent;
import commands.ICommand;
import music.PlayerManager;

import static util.AllowedToPlayMusic.allowedToPlayMusic;

public class Seek implements ICommand {
    CommandReceivedEvent e;
    CommandEnum commandEnum = new CommandEnum();

    String command = "seek";
    String commandAlias = "seek";
    String category = "music";
    String exampleCommand = "`!seek (time in seconds)`";
    String shortCommandDescription = "Set the current playing track to a specific time.";
    String fullCommandDescription = "Set the current playing track to a specific time.";

    @Override
    public void command(CommandReceivedEvent event) {
        e = event;

        if (!e.hasArgs()) {
            e.getMessageChannel().sendMessage(getFullHelp("Requires at least 1 argument")).queue();
            return;
        }

        if (!allowedToPlayMusic(e, "seek")) return;

        if (e.getArgs()[0].matches("\\d+")) {
            PlayerManager manager = PlayerManager.getInstance();
            manager.setSongPosition(e.getGuild(), Long.parseLong(e.getArgs()[0]));
        } else {
            e.getMessageChannel().sendMessage(commandEnum.getFullHelpItem("seek").setDescription("Give a valid number").build()).queue();
        }
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
