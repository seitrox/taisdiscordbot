package commands.bot;

import commands.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Shutdown implements ICommand {
    GuildMessageReceivedEvent e;

    String command = "shutdown";
    String commandAlias = "sd";
    String category = "general";
    String exampleCommand = "`!shutdown`";
    String shortCommandDescription = "Stop the bot";
    String fullCommandDescription = "Stop the bot";

    @Override
    public void command(GuildMessageReceivedEvent event, String[] args) {
        e = event;

        System.exit(1);
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