package commands.music;

import commands.CommandEnum;
import commands.CommandReceivedEvent;
import commands.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class Join implements ICommand {
    CommandReceivedEvent e;
    CommandEnum commandEnum = new CommandEnum();
    AudioManager audioManager;

    String command = "join";
    String commandAlias = "join";
    String category = "music";
    String exampleCommand = "`!join`";
    String shortCommandDescription = "Joins the bot in a voice channel.";
    String fullCommandDescription = "Joins the bot in a voice channel.";

    @Override
    public void command(CommandReceivedEvent event) {
        e = event;

        audioManager = e.getGuild().getAudioManager();

        if (audioManager.isConnected()) {
            e.getMessageChannel().sendMessage(commandEnum.getFullHelpItem("join").setDescription("Error: Already connected to a channel.").build()).queue();
            return;
        }

        if (joinChannel(e)) {
            e.getMessageChannel().sendMessage("Joining your voice channel").queue();
        }
    }


    public boolean joinChannel(CommandReceivedEvent e) {

        audioManager = e.getGuild().getAudioManager();

        GuildVoiceState memberVoiceState = e.getMember().getVoiceState();

        if (!memberVoiceState.inVoiceChannel()){
            e.getMessageChannel().sendMessage(commandEnum.getFullHelpItem("userinfo").setDescription("Error: Join a voice channel before running this command.").build()).queue();
            return false;
        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        Member selfMember = e.getGuild().getSelfMember();

        if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
            e.getMessageChannel().sendMessage(commandEnum.getFullHelpItem("userinfo").setDescription("Error: Missing VOICE_CONNECT permission").build()).queue();
            return false;
        }

        audioManager.openAudioConnection(voiceChannel);
        return true;
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
