package app.controllers;

import app.entities.*;
import app.exceptions.DatabaseException;
import app.persistence.*;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    public static void addRouts(Javalin app, ConnectionPool connectionPool) {
        app.post("/login", ctx -> login(ctx, connectionPool));
        app.get("/login-page", ctx -> ctx.render("/login.html"));
        app.get("/create-user-page", ctx -> ctx.render("/create-user.html"));
        app.post("/create-user", ctx -> createUser(ctx, connectionPool));
        app.get("/index", ctx -> addAllParts(ctx, connectionPool));
        app.get("/home-page", ctx -> renderHomePage(ctx, connectionPool));
        app.get("/admin-page", ctx -> renderAdminPage(ctx, connectionPool));
        app.get("/order-page", ctx -> renderOrderPage(ctx, connectionPool));
        app.post("/bestil", ctx -> saveOrderInSession(ctx, connectionPool));
    }

    private static void renderHomePage(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User user = ctx.sessionAttribute("currentUser");
        ctx.attribute("user", user);
        addAllParts(ctx, connectionPool);
        ctx.render("home-page.html");
    }

    private static void renderAdminPage(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        User user = ctx.sessionAttribute("currentUser");
        if (user == null) {
            ctx.render("home-page.html");
            return;
        }
        if (user.getRole() == Role.ADMIN) {
            ctx.attribute("user", user);
            List<Order> orders = OrderMapper.getAllOrders(connectionPool);
            ctx.attribute("orders", orders);
            ctx.render("user-profiles.html");
        }
    }

    public static void renderOrderPage(Context ctx, ConnectionPool connectionPool){
        User user = ctx.sessionAttribute("CurrentUser");
        ctx.attribute("user", user);
        List<Orderline> orderlineList = ctx.sessionAttribute("currentOrder");
        ctx.attribute("cart", orderlineList);
        ctx.render("order-page.html");
    }

    private static void createUser(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        String passwordRepeat = ctx.formParam("repeat-password");
        if (password == null) {
            ctx.attribute("errorMsg", "Password må ikke være tomt");
            ctx.render("create-user.html");
            return;
        } else if (!password.equals(passwordRepeat)) {
            ctx.attribute("errorMsg", "Passwords matcher ikke!");
            ctx.render("create-user.html");
            return;
        }
        try {
            User user = UserMapper.createUser(email, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            ctx.redirect("/home-page");
        } catch (DatabaseException e) {
            throw new DatabaseException("Problem ved oprettelse af bruger", e.getMessage());
        }
    }

    private static void login(Context ctx, ConnectionPool connectionPool) {
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        try {
            User user = UserMapper.login(email, password, connectionPool);
            if (user == null) {

            }
            ctx.sessionAttribute("currentUser", user);
            ctx.redirect("/home-page");
        } catch (DatabaseException e) {
            ctx.attribute("errorMsg", "Brugernavn eller password forkert");
            ctx.render("login.html");
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

    private static void saveOrderInSession(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        int topID = Integer.parseInt(ctx.formParam("top-id"));
        int bottomID = Integer.parseInt(ctx.formParam("bottom-id"));
        int amount = Integer.parseInt(ctx.formParam("amount"));

        System.out.println("Top: " + topID + " Bottom: " + bottomID + " Antal: " + amount);

        List<Orderline> currentOrder = ctx.sessionAttribute("currentOrder");
        if (null == currentOrder) {
            currentOrder = new ArrayList<>();
        }
        Bottom bottom = BottomMapper.getBottomByID(connectionPool, bottomID);
        Topping topping = TopMapper.getToppingByID(connectionPool, topID);
        Cupcake cupcake = new Cupcake(bottom, topping);
        currentOrder.add(new Orderline(cupcake, amount));

        ctx.sessionAttribute("currentOrder", currentOrder);
        ctx.redirect("/home-page");

    }
}
