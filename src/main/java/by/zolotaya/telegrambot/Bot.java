package by.zolotaya.telegrambot;

import by.zolotaya.telegrambot.dao.CityDAO;
import by.zolotaya.telegrambot.model.City;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private static final Logger LOG = LogManager.getLogger(Bot.class);
    private static final String BOT_NAME = "ApollinariaBot";
    private static final String BOT_TOKEN = "1625385211:AAHZz7HLy8RR5hUgzmnS9JER8UDgDPNoBgM";
    private CityDAO cityDAO = new CityDAO();

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()){
            int counter = 0;
            if (message.getText().equals("/start")){
                LOG.info("Command /start");
                sendMsg(message, "What city are you going to visit?");
                counter = 1;
            }
            for (City city : cityDAO.getAllCities()){
                if (city.getName().equalsIgnoreCase(message.getText())){
                    LOG.info("Need info about " + city.getName());
                    sendMsg(message, city.getDescription());
                    counter = 1;
                }
            }
            if (counter < 1){
                LOG.info("Unacceptable name of city");
                sendMsg(message, "We don't have advices about " + message.getText()
                + "\n Choose from the list:\n " + cityDAO.cityList());
            }

        }

    }

    private synchronized void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Error while sending message from bot", e);
        }
    }
    public void botConnect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
            LOG.info("TelegramAPI started. Look for messages");
        } catch (TelegramApiException e) {
            LOG.error("Cant Connect.  " + e.getMessage());
        }
        }
}
