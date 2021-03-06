package lk.sparkx.ncms;

import com.google.gson.JsonObject;
import lk.sparkx.ncms.repository.ObjectRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by: thisum
 * Date      : 2020-08-16
 * Time      : 22:45
 **/

@WebServlet(name = "HelloWorld")
public class HelloWorld extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String data = ObjectRepo.getInstance().getData();
        this.sendResponse(data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String key = req.getParameter("key");
        String value = req.getParameter("value");

        ObjectRepo.getInstance().addData(key, value);
        String data = ObjectRepo.getInstance().getData();
        this.sendResponse(data, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String key = req.getParameter("key");
        String value = req.getParameter("value");

        ObjectRepo.getInstance().updateData(key, value);
        String data = ObjectRepo.getInstance().getData();
        this.sendResponse(data, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String key = req.getParameter("key");

        ObjectRepo.getInstance().deleteData(key);
        String data = ObjectRepo.getInstance().getData();
        this.sendResponse(data, resp);
    }

    /**
     * Send response using JsonObject. Best to use when the response contains 1-2 parameters
     *
     * @param data
     * @param resp
     * @throws IOException
     */
    private void sendResponse(String data, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("Response", data);
        writer.print(json.toString());
        writer.flush();
    }

}
