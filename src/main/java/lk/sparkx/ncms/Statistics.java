package lk.sparkx.ncms;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by: thisum
 * Date      : 2020-08-17
 * Time      : 00:06
 **/

@WebServlet(name = "Statistics")
public class Statistics extends HttpServlet
{

    /**
     * Send response using Gson library -> recommended to use when the response has more than 2 parameters
     * Check Gson examples for more details
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String target = req.getParameter("who");
        String statistics = "";
        if("general".equals(target))
        {
            statistics = this.getStatisticsFroGeneral();
        }
        else if("moh".equals(target))
        {
            statistics = this.getStatisticsForMOH();
        }
        else if("hospital".equals(target))
        {
            statistics = this.getStatisticsForHospital();
        }
        else
        {
            statistics = "Wrong parameters";
        }

        ResponseObj responseObj = new ResponseObj();
        responseObj.setResponse(statistics);

        String responseString = new Gson().toJson(responseObj);


        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print(responseString);
        writer.flush();
    }

    private String getStatisticsFroGeneral()
    {
        return "Statistics for general public";
    }

    private String getStatisticsForMOH()
    {
        return "Statistics for MOH";
    }

    private String getStatisticsForHospital()
    {
        return "Statistics for hospitals";
    }
}
