package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.service.Impl.ArtistServiceImpl;
import mk.ukim.finki.wp.lab.service.Impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "artist", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final ArtistServiceImpl artistService;
    private final SongServiceImpl songService;
    public ArtistServlet(SpringTemplateEngine templateEngine, ArtistServiceImpl artistService, SongServiceImpl songService) {
        this.templateEngine = templateEngine;
        this.artistService = artistService;
        this.songService = songService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext webContext = new WebContext(iWebExchange);
        webContext.setVariable("artistList", artistService.listArtists());
        templateEngine.process("artistList", webContext, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackID;
        List<Artist> artistList;
        artistList = artistService.listArtists();

        if(req.getParameter("songRadio") != null){
            trackID = req.getParameter("songRadio");
        }else{
            trackID = null;
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext webContext = new WebContext(iWebExchange);
        webContext.setVariable("trackID", trackID);
        webContext.setVariable("artistList", artistList);
        templateEngine.process("artistList", webContext, resp.getWriter());
    }
}
