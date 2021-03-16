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

//        for (int i = 0; i < 8; i++) {
//            var note = Theory.randomNote();
//            System.out.println("note " + note);
//            System.out.println("next " + note.next());
//            System.out.println(note.midiNum());
//            System.out.println("trans " + Theory.transpose(note, 11) );
//        }

//        System.out.println(Note.fromName("C#4").isEnharmonicWith(Note.fromName("Db4")));
//
//        var notes = Theory.fretboardNotes(Tuning.STANDARD_GUITAR, 0, 4);
//        System.out.println(notes);

        var coord = new FretCoord(1, 2);
        var notes = Theory.fretboardNotes(Tuning.STANDARD_GUITAR, 0, 4);

        System.out.println(Theory.findNoteAt(coord, Tuning.STANDARD_GUITAR, notes));
    }
}
