package com.yourcompany.shoebot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ShoeSupportBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        // Your bot username
        return "APminibot";
    }

    @Override
    public String getBotToken() {
        // Your bot token from BotFather
        return "6994163511:AAE5T3bulduIh0WwQldkeDAN8SLfePy5YGs";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            String response = processMessage(update.getMessage().getText());
            message.setText(response);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String processMessage(String messageText) {
        messageText = messageText.toLowerCase();

        switch (messageText) {
            case "/start":
                return "Welcome to ShoeBot! How can I help you? Here are some things you can ask about: shoe size, models, color, store locations, contact numbers.";
            case "shoe size":
                return "Our shoes are available in sizes 6 to 12. What size are you interested in?";
            case "models":
                return "We have several models available: Runner, Sneaker, Classic. Which are you interested in?";
            case "color":
                return "Our shoes come in a variety of colors: red, blue, black, white. Which color do you prefer?";
            case "store locations":
                return "Our stores are located in Visakhapatnam, Pathankot, Chandigarh";
            case "contact numbers":
                return "You can reach us at 9550578004 for support. What query do you have?";
            case "returns":
                return "You can return your shoes within 30 days of purchase. Please visit our website or contact support for assistance.";
            case "promotions":
                return "Check out our latest promotion: Buy one get one 50% off! Available until the end of this month.";
            default:
                return "I'm not sure how to answer that. Can you try asking something else?";
        }
    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new ShoeSupportBot());
            System.out.println("ShoeSupportBot is now running!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
