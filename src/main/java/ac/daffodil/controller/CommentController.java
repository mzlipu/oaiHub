package ac.daffodil.controller;

import ac.daffodil.dao.CommentDao;
import ac.daffodil.dao.FileDao;
import ac.daffodil.model.Comments;
import ac.daffodil.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Created by Bithi on 4/2/2018.
 */
@Controller
public class CommentController {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    CommentDao commentDao;

    @Autowired
    FileDao fileDao;

    @RequestMapping(value = { "/comment" }, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        Comments newComment=new Comments();
        modelAndView.addObject("newComment", newComment);
        modelAndView.addObject("comments", commentDao.getAll());
        modelAndView.setViewName("comment");
        return modelAndView;
    }



    @RequestMapping(value = { "/comment/saveComment" }, method = RequestMethod.POST)
    public String saveComment(Comments comments) {
        commentDao.save(comments);
        return "redirect:/comment";
    }


    @RequestMapping(value={"/comment/find/{comment_id}"}, method = RequestMethod.GET)
    public ModelAndView findForEditComment(@PathVariable(required = true, name = "comment_id") Long comment_id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Comments> comments= commentDao.find(comment_id);
        System.out.println(comments);
        modelAndView.addObject("newComment", comments.get());
        modelAndView.addObject("comments", commentDao.getAll());
        modelAndView.setViewName("comment");
        return modelAndView;
    }


    @RequestMapping(value="/comment/delete/{comment_id}", method = RequestMethod.GET)
    public String deleteExam(@PathVariable(required = true, name = "comment_id") Long comment_id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Comments> comments= commentDao.find(comment_id);
        commentDao.delete(comments.get());
        modelAndView.addObject("message", " Data Has Been Deleted...");
        return "redirect:/comment";
    }



}
