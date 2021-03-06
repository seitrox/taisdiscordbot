package commands.fun;

import commands.CommandReceivedEvent;
import commands.ICommand;

public class Ping implements ICommand {
    String command = "ping";
    String commandAlias = "pong";
    String category = "fun";
    String exampleCommand = "`!ping`";
    String shortCommandDescription = "Get a real ping";
    String fullCommandDescription = "Not much to say, just a command that gives your totally not fake ping.";

    public void command(CommandReceivedEvent event) {
        event.getMessageChannel().sendMessage("Ping: " + event.getMember().getJDA().getGatewayPing() + "ms").queue();
    }

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
