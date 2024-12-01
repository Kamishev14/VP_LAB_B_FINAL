package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.Impl.ArtistServiceImpl;
import mk.ukim.finki.wp.lab.service.Impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(name = "songDetails", urlPatterns = "/song-details")
public class SongDetails extends HttpServlet {


    private final SpringTemplateEngine templateEngine;
    private final SongServiceImpl songService;
    private final ArtistServiceImpl artistService;

    public SongDetails(SpringTemplateEngine templateEngine, SongServiceImpl songService, ArtistServiceImpl artistService) {
        this.templateEngine = templateEngine;
        this.songService = songService;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Song song = songService.listSongs().stream().findFirst().orElse(null);
        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("entity", song);
        templateEngine.process("songDetails", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackID = req.getParameter("trackID");
        String artistID = req.getParameter("artistID");
        Song song = songService.listSongs().stream().findFirst().orElse(null);

        if(trackID != null && artistID != null){
            song = songService.findByTrackId(trackID);
            Artist artist =  artistService.findArtistById(Long.valueOf(artistID));
            song.addPerformer(artist);
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("entity", song);
        templateEngine.process("songDetails", context, resp.getWriter());
    }

}
