package commands.music;

import commands.CommandReceivedEvent;
import commands.ICommand;
import music.PlayerManager;
import music.youtube.SearchYouTube;

import java.net.MalformedURLException;
import java.net.URL;

import static util.AllowedToPlayMusic.allowedToPlayMusic;

public class PlayTop implements ICommand {
    CommandReceivedEvent e;

    String url;

    String command = "playtop";
    String commandAlias = "playtop";
    String category = "music";
    String exampleCommand = "`!playtop <URL>`";
    String shortCommandDescription = "Plays music, and song goes to top of the queue.";
    String fullCommandDescription = "Plays music, and song goes to the top of the queue.";

    @Override
    public void command(CommandReceivedEvent event) {
        e = event;

        if (!e.hasArgs()) {
            e.getMessageChannel().sendMessage(getFullHelp("Error: requires at least 1 argument")).queue();
            return;
        }

        if (!allowedToPlayMusic(e, "playtop")) return;


        String input = e.getMessageWithoutCommand();

        if (!isUrl(input)) {
            if (!searchByName(input)) return;
        } else {
            url = input;
        }

        PlayerManager manager = PlayerManager.getInstance();

        manager.loadAndPlay(e.getTextChannel(), url, true, e.getAuthor().getId(), e.getAuthor().getAsTag());
    }

    private boolean isUrl(String input) {
        try {
            new URL(input);
            return true;
        } catch (MalformedURLException malformedURLException) {
            return false;
        }
    }

    private boolean searchByName(String input) {
        SearchYouTube searchYouTube = new SearchYouTube();

        String videoUrl = searchYouTube.getVideoUrl(input);

        if (videoUrl.startsWith("Error:")) {
            e.getMessageChannel().sendMessage(getFullHelp(videoUrl)).queue();
            return false;
        }

        url = videoUrl;
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
