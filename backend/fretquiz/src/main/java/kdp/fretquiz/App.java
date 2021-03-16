package kdp.fretquiz;

import io.javalin.Javalin;
import kdp.fretquiz.theory.*;
import kdp.fretquiz.user.UserController;
import kdp.fretquiz.user.UserDao;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
    private static final int PORT = 8080;
    private static final String STATIC_FILE_PATH = "/public";

    public static UserDao userDao = new UserDao();

    public static void main( String[] args ) {
//        var app = Javalin.create(config -> {
//            config.addStaticFiles(STATIC_FILE_PATH);
//        });
//
//        app.routes(() -> {
//            path("api", () -> {
//                path("user", () -> {
//                    get("all", UserController.getAll);
//                    post("login", UserController.handleLogin);
//                });
//            });
//        });

//        app.start(PORT);

//        System.out.println(Note.fromName("C#4").next());
//        System.out.print(Accidental.fromName("#"));

//        for (int i = 0; i < 8; i++) {
//            var note = Theory.randomNote();
//            System.out.println("note " + note);
//            System.out.println("next " + note.next());
//            System.out.println("trans " + Theory.transpose(note, 11) );
//            System.out.println(note.midiNum());
//        }

        var notes = Theory.fretboardNotes(Tuning.STANDARD_GUITAR, 0, 4);
        System.out.println(notes);
    }
}
