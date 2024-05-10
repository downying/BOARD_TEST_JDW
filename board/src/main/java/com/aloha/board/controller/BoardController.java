package com.aloha.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.board.dto.Board;
import com.aloha.board.server.BoardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    
    @Autowired
    private BoardService boardService;

    /**
     * 목록 화면    [GET]
     * 조회 화면    [GET]
     * 등록 화면    [GET]
     * 등록 처리    [POST]
     * 수정 화면    [GET]
     * 수정 처리    [POST]
     * 삭제 처리    [POST]
     */

     /**
      * 목록 화면
      * @param model
      * @return
      * @throws Exception
      */
     @GetMapping("/list")
     public String list(Model model) throws Exception {
        log.info("목록 화면....");

        // 데이터 요청
        List<Board> boardList = boardService.list();
        // 모델 등록
        model.addAttribute("boardList", boardList);
        // 뷰페이지 지정
        return "/board/list";
    }

    /**
     * 조회 화면
     * @param no
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/read")
    public String select(@RequestParam("no") int no, Model model) throws Exception {
        log.info("조회 화면....");

        // 데이터 요청
        Board board = boardService.select(no);
        // 모델 등록
        model.addAttribute("board", board);
        // 뷰페이지 지정
        return "/board/read";
    }

    /**
     * 등록 화면
     * @return
     */
    @GetMapping("/insert")
    public String insert() {
        log.info("등록 화면....");

        return "/board/insert";
    }

    /**
     * 등록 처리
     * @param board
     * @return
     * @throws Exception 
     */
    @PostMapping("/insert")
    public String insertPro(Board board) throws Exception {
        // 데이터 요청
        int result = boardService.insert(board);
        // 데이터 처리 성공 시
        if( result > 0 ) {
            return "redirect:/board/list";
        }    
        // 데이터 처리 실패 시
        return "redirect:/board/insert?error";
    }
    
    /**
     * 수정 화면
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/update")
    public String update(@RequestParam("no") int no, Model model) throws Exception {
        log.info("수정 화면....");

        // 데이터 요청
        Board board = boardService.select(no);
        // 모델 등록
        model.addAttribute("board", board);
        // 뷰페이지 지정
        return "/board/update";
    }

    /**
     * 수정 처리
     * @param board
     * @return
     * @throws Exception 
     */
    @PostMapping("/update")
    public String updatetPro(Board board) throws Exception {
        // 데이터 요청
        int result = boardService.update(board);
        // 데이터 처리 성공 시
        if( result > 0 ) {
            return "redirect:/board/list";
        }    
        // 데이터 처리 실패 시
        int no = board.getNo();
        return "redirect:/board/update?no=" + no + "&error";
    }

    /**
     * 삭제 처리
     * @param board
     * @return
     * @throws Exception 
     */
    @PostMapping("/delete")
    public String deletetPro(@RequestParam("no") int no) throws Exception {
        // 데이터 요청
        int result = boardService.delete(no);
        // 데이터 처리 성공 시
        if( result > 0 ) {
            return "redirect:/board/list";
        }    
        // 데이터 처리 실패 시
        return "redirect:/board/delete?no=" + no + "&error";
    }
    
    
    
     
}
