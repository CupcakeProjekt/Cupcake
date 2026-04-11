package app.controllers;

import app.entities.*;
import app.exceptions.DatabaseException;
import app.persistence.*;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserController {

    public static void addRouts(Javalin app, ConnectionPool connectionPool) {
        app.post("/login", ctx -> login(ctx, connectionPool));
        app.post("/create-user", ctx -> createUser(ctx, connectionPool));
        app.get("/index", ctx -> addAllParts(ctx, connectionPool));

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

    private static void addAllParts(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        List<Topping> tops = TopMapper.getAllTops(connectionPool);
        List<Bottom> bottoms = BottomMapper.getAllBottoms(connectionPool);
        ctx.attribute("tops", tops);
        ctx.attribute("bottoms", bottoms);
    }

    private static void addOrderToDatabase(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        int topID = Integer.parseInt(ctx.formParam("top-id"));
        int bottomID = Integer.parseInt(ctx.formParam("bottom-id"));
        int amount = Integer.parseInt(ctx.formParam("amount"));

        OrderMapper.addOrderlineToOrder(connectionPool, bottomID, topID);




    }
}
