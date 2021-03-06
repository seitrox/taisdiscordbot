package commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import commands.CommandEnum;
import commands.CommandReceivedEvent;
import commands.ICommand;
import music.GuildMusicManager;
import music.PlayerManager;

import static util.AllowedToPlayMusic.allowedToPlayMusic;

public class Loop implements ICommand {
    CommandReceivedEvent e;
    CommandEnum commandEnum = new CommandEnum();

    String command = "loop";
    String commandAlias = "loop";
    String category = "music";
    String exampleCommand = "`!loop all/1`";
    String shortCommandDescription = "Loop the current queue / current song.";
    String fullCommandDescription = "Loop the current queue / current song.\n" +
            "`!loop all` to loop the current queue. \n" +
            "`!loop 1` to loop the current playing song.\n" +
            "Run the command again to toggle it off, or use `!loop off`";

    public void command(CommandReceivedEvent event) {
        e = event;

        if (!allowedToPlayMusic(e, "loop")) return;

        if (!e.hasArgs()) {
            e.getMessageChannel().sendMessage(commandEnum.getFullHelpItem("loop").setDescription("Requires at least 1 argument").build()).queue();
            return;
        }

        PlayerManager manager = PlayerManager.getInstance();

        switch (e.getArgs()[0]) {
            case "1":
            case "song":
                manager.loopSongToggle(e.getGuild());
                GuildMusicManager musicManager = manager.getGuildMusicManager(e.getGuild());
                AudioTrack playingTrack = musicManager.player.getPlayingTrack();

                e.getMessageChannel().sendMessage("Succesfully looping: " + playingTrack.getInfo().uri).queue();
                break;
            case "all":
            case "queue":
                manager.loopQueueToggle(e.getGuild());
                e.getMessageChannel().sendMessage("Succesfully looping the queue").queue();
                break;
            case "off":
                manager.loopOff(e.getGuild());
                break;
            default:
                e.getMessageChannel().sendMessage(getFullHelp("Error: either give 1 or all as input")).queue();
                break;
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
