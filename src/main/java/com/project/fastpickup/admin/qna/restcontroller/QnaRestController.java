package com.project.fastpickup.admin.qna.restcontroller;

import com.project.fastpickup.admin.qna.dto.QnaDTO;
import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.qna.dto.QnaRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaUpdateDTO;
import com.project.fastpickup.admin.qna.service.QnaService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/qna/")
@CrossOrigin
public class QnaRestController {

    private final QnaService qnaService;

    // list
    @GetMapping("list/{email}")
    public PageResponseDTO<QnaListDTO> list(@PathVariable("email") String email, PageRequestDTO pageRequestDTO) {

        return qnaService.listQna(email,pageRequestDTO);
    }

    // create
    @PostMapping("create")
    public Map<String, Integer> create(@RequestBody QnaRegistDTO qnaRegistDTO) {

        int result = qnaService.createQna(qnaRegistDTO);
        return Map.of("result", result);
    }

    // read
    @GetMapping("read/{qno}")
    public QnaDTO read(@PathVariable("qno") Long qno) {

        return qnaService.readQna(qno);
    }

    // update
    @PutMapping("update")
    public Map<String, Integer> update(@RequestBody QnaUpdateDTO qnaUpdateDTO) {

        int result = qnaService.updateQna(qnaUpdateDTO);

        return Map.of("result", result);
    }

    // delete
    @DeleteMapping("delete/{qno}")
    public Map<String, Integer> delete(@PathVariable("qno") Long qno) {

        int result = qnaService.deleteQna(qno);

        return Map.of("result", result);
    }

}
