package app.controllers;

import app.entities.Bottom;
import app.entities.Topping;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.BottomMapper;
import app.persistence.ConnectionPool;
import app.persistence.TopMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    public static void addRouts(Javalin app, ConnectionPool connectionPool) {
        app.post("/login", ctx -> login(ctx, connectionPool));
        app.post("/create-user", ctx -> createUser(ctx, connectionPool));
        app.get("/index", ctx -> {
            List<Topping> tops = TopMapper.getAllTops(connectionPool);
            List<Bottom> bottoms = BottomMapper.getAllBottoms(connectionPool);
            ctx.attribute("tops", tops);
            ctx.attribute("bottoms", bottoms);
        });

    }

    private static void createUser(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        try {
            User user = UserMapper.createUser(username, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            ctx.redirect("/main-page");
        } catch (DatabaseException e) {
            throw new DatabaseException("Problem ved oprettelse af bruger", e.getMessage());
        }
    }

    private static void login(Context ctx, ConnectionPool connectionPool) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        try {
            User user = UserMapper.login(username, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            ctx.redirect("/");
        } catch (DatabaseException e) {
            ctx.attribute("msg", e.getMessage());
            ctx.redirect("/");
        }
    }

    private static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }
}
