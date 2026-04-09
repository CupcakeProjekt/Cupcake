package app.controllers;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    public static void addRouts(Javalin app, ConnectionPool connectionPool) {
    app.post("/login", ctx -> login(ctx, connectionPool));
    }
private static void login(Context ctx, ConnectionPool connectionPool){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

}
}
