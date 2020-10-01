package commands.music;

import commands.CommandEnum;
import commands.ICommand;
import music.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Resume implements ICommand {
    GuildMessageReceivedEvent e;
    CommandEnum commandEnum = new CommandEnum();

    String command = "resume";
    String commandAlias = "resume";
    String category = "music";
    String exampleCommand = "`!resume`";
    String shortCommandDescription = "Start the music again";
    String fullCommandDescription = "Get the queue of the music.";

    @Override
    public void command(GuildMessageReceivedEvent event, String[] args) {
        e = event;

        AudioManager audioManager = e.getGuild().getAudioManager();

        if (!audioManager.isConnected()) {
            e.getChannel().sendMessage(commandEnum.getFullHelpItem("resume").setDescription("Error: bot isn't connected to a voice channel.").build()).queue();
            return;
        }

        GuildVoiceState voiceState = e.getMember().getVoiceState();

        if (!voiceState.inVoiceChannel()) {
            e.getChannel().sendMessage(commandEnum.getFullHelpItem("resume").setDescription("Error: you aren't connected to a voice channel.").build()).queue();
            return;
        }

        if (!(audioManager.getConnectedChannel() == voiceState.getChannel())) {
            e.getChannel().sendMessage(commandEnum.getFullHelpItem("resume").setDescription("Error: you aren't in the same channel as the bot.").build()).queue();
            return;
        }

        PlayerManager manager = PlayerManager.getInstance();

        manager.resume(e);
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