package kdp.fretquiz.chat;

import io.javalin.websocket.WsMessageContext;
import kdp.fretquiz.game.GameController;
import kdp.fretquiz.user.UserController;
import kdp.fretquiz.websocket.Request;
import kdp.fretquiz.websocket.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static kdp.fretquiz.App.chatDao;

public class ChatController
{
    public static Logger log = LoggerFactory.getLogger(ChatController.class);

    public static void handleChatMessage(WsMessageContext context)
    {
        final var user = UserController.getUserFromContext(context);
        final var message = context.message(Request.ChatMessage.class);

        final var gameId = message.gameId();
        final var text = message.text();
        final var chatMessage = new ChatMessage(gameId, user, text);

        chatDao.save(chatMessage);
        final var messages = chatDao.getMessages(gameId);

        GameController.notifyPlayers(gameId, new Response.UpdateChat(messages));
    }
}
