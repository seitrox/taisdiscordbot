package commands.general;

import commands.CommandReceivedEvent;
import commands.ICommand;
import database.user.DatabaseUser;
import net.dv8tion.jda.api.EmbedBuilder;

public class Leaderboard implements ICommand {
    CommandReceivedEvent e;
    DatabaseUser databaseUser = new DatabaseUser();

    String command = "leaderboard";
    String commandAlias = "leaderboard";
    String category = "general";
    String exampleCommand = "`!leaderboard`";
    String shortCommandDescription = "Get the ranking of players";
    String fullCommandDescription = "Get the ranking of players";

    @Override
    public void command(CommandReceivedEvent event) {
        e = event;

        EmbedBuilder eb = databaseUser.topTenLeaderboard(e);

        eb.setTitle("Leaderboard");

        e.getMessageChannel().sendMessage(eb.build()).queue();
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
