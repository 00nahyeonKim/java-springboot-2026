package com.pknu26.studygroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pknu26.studygroup.dto.Board;
import com.pknu26.studygroup.dto.LoginUser;
import com.pknu26.studygroup.dto.PageRequest;
import com.pknu26.studygroup.service.BoardService;
import com.pknu26.studygroup.service.ReplyService;
import com.pknu26.studygroup.validation.BoardForm;
import com.pknu26.studygroup.validation.ReplyForm;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

//  Controller에서 중요한 것은 mapping url. 메서드명 중요하지 않음.
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ReplyService replyService;

    // 목록, 페이징 용 변경
    @GetMapping("/list")
    public String list(@ModelAttribute PageRequest pageRequest, Model model) {
        model.addAttribute("response", this.boardService.readBoardList(pageRequest));
        return "/board/list"; // board 폴더 밑에 위치한 list.html을 리턴하라
    }

    // 목록상세보기
    @GetMapping("/detail/{boardId}")
    public String detail(@PathVariable("boardId") Long boardId, Model model) {
        Board board = this.boardService.readBoardById(boardId);
        model.addAttribute("board", board);
        model.addAttribute("replyList", replyService.getReplyListByBoardId(boardId));

        ReplyForm replyForm = new ReplyForm();
        replyForm.setBoardId(boardId);
        model.addAttribute("replyForm", replyForm);
        return "/board/detail";
    }

    // 글쓰기 GET
    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");

        
        if (loginUser == null) {
            return "redirect:/user/login";
        }        
        
        BoardForm boardForm = new BoardForm();
        boardForm.setWriter(loginUser.getName());
        boardForm.setWriterId(loginUser.getLoginId());

        model.addAttribute("boardForm", boardForm);

        return "/board/form";
    }

    // 글쓰기 POST
    @PostMapping("/create")
    public String create(@Valid BoardForm boardForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/board/form";
        } 

        this.boardService.createBoard(boardForm);
        return "redirect:/board/list";
    }
    
    // 글수정 GET
    @GetMapping("/edit/{boardId}")
    public String showEditForm(@PathVariable("boardId") Long boardId, Model model, HttpSession session) {
        Board board = boardService.readBoardById(boardId);

        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");

        
        if (loginUser == null) {
            return "redirect:/user/login";
        }   

        BoardForm boardForm = new BoardForm();
        boardForm.setBoardId(board.getBoardId());
        boardForm.setTitle(board.getTitle());
        boardForm.setContent(board.getContent());
        boardForm.setWriter(loginUser.getName());
        boardForm.setWriterId(loginUser.getLoginId());


        model.addAttribute("boardForm", boardForm);
        return "/board/form";
    }

    // 글수정 POST
    @PostMapping("/edit/{boardId}")
    public String edit(@PathVariable("boardId") Long boardId,
                       @Valid @ModelAttribute("boardForm") BoardForm boardForm,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/board/form";
        }

        boardForm.setBoardId(boardId);
        this.boardService.updateBoard(boardForm);
        return "redirect:/board/detail/" + boardId; // 수정된 자신글 상세로 이동
    }

    // 글삭제 POST
    @PostMapping("/delete/{boardId}")
    public String delete(@PathVariable("boardId") Long boardId) {
        this.boardService.deleteBoard(boardId);
        return "redirect:/board/list";
    }

}
