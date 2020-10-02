package commands.music;

import commands.CommandEnum;
import commands.ICommand;
import database.guild.DatabaseGuild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class dQw implements ICommand {
    GuildMessageReceivedEvent e;
    DatabaseGuild databaseGuild = new DatabaseGuild();

    String command = "dQw";
    String commandAlias = "dQw";
    String category = "music";
    String exampleCommand = "`!dQw`";
    String shortCommandDescription = "HAHAHAHAHA IMAGINE GETTING RICKROLLED LOL.";
    String fullCommandDescription = "No hate ly";

    @Override
    public void command(GuildMessageReceivedEvent event, String[] args) {
        e = event;
        if (args[0].equals("dQw")) {
            RickRoll rickRoll = new RickRoll();
            rickRoll.command(e, args);
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