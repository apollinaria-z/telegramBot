import by.zolotaya.telegrambot.Bot;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;

public class AppBot {
    private static final Logger LOG = LogManager.getLogger(AppBot.class);
    public static void main(String[] args) {

        LOG.info("Initializing API context...");
        ApiContextInitializer.init();
        Bot test_bot = new Bot();
        test_bot.botConnect();
    }
}
