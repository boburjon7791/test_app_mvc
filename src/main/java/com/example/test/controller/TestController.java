package com.example.test.controller;

import com.example.test.dtos.*;
import com.example.test.exceptions.NotFoundException;
import com.example.test.services.AnswerService;
import com.example.test.services.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/tests")
public class TestController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    @GetMapping("/create/answer")
    public String createByName(){
        return "answer/answer_create";
    }
    @PostMapping("/create/answer")
    public String create(@ModelAttribute @Valid AnswerDto dto,Model model){
        AnswerDto answerDto = answerService.create(dto);
        model.addAttribute("answer",List.of(answerDto));
        return "answer/answers";
    }
    @GetMapping("/get/answer")
    public String getAnswer(Model model, @RequestParam("id") long id){
        AnswerDto answerDto = answerService.get(id);
        model.addAttribute("answer",List.of(answerDto));
        return "answer/answers";
    }
    @PostMapping("/update/answer")
    public String updateAnswer(Model model,@ModelAttribute @Valid AnswerDto dto){
        AnswerDto update = answerService.update(dto);
        model.addAttribute("answers",List.of(update));
        return "answer/answers";
    }
    @GetMapping("/list/answer")
    public String listAnswer(Model model){
        List<AnswerDto> answers = answerService.answers();
        if (answers.isEmpty()) {
            throw new NotFoundException("Javoblar topilmadi");
        }
        model.addAttribute("answers",answers);
        return "answer/answers";
    }
    @GetMapping("/list-by-name/answer")
    public String listAnswer(Model model,@RequestParam("name") String name){
        List<AnswerDto> answers= answerService.answersByName(name);
        if (answers.isEmpty()) {
            throw new NotFoundException("Javoblar topilmadi");
        }
            model.addAttribute("answers",answers);
            return "answer/answers";
        
    }
    @GetMapping("/create/question")
    public String createQ(){
        return "question/question_create";
    }
    @PostMapping("/create/question")
    public String create(Model model, @Valid @ModelAttribute QuestionCreateDto dto){
        questionService.create(dto);
        List<QuestionGetDto> questions = questionService.questions();
        if (questions.isEmpty()) {
            throw new NotFoundException("Savollar topilmadi");
        }
        model.addAttribute("questions",questions);
        return "question/questions_edit";
    }
    @GetMapping("/get/question")
    public String get(Model model,@RequestParam("id") long id){
        QuestionGetDto getDto = questionService.get(id);
        System.out.println("Hello from question get API");
        model.addAttribute("questions",List.of(getDto));
        return "question/questions_edit";
    }
    @PostMapping("/update/question")
    public String update(@Valid @ModelAttribute QuestionUpdateDto dto,Model model){
        questionService.update(dto);
        List<QuestionGetDto> questions = questionService.questions();
        if (questions.isEmpty()) {
            throw new NotFoundException("Savollar topilmadi");
        }
        model.addAttribute("questions", questions);
        return "question/questions_edit";
    }
    @GetMapping("/list/question")
    public String list(Model model){
        List<QuestionGetDto> questions = questionService.questions();
        if (questions.isEmpty()) {
            throw new NotFoundException("Savollar topilmadi");
        }
        model.addAttribute("questions",questions);
        return "question/questions_edit";
    }
    @GetMapping("/list-by-name/question")
    public String list(Model model,@RequestParam("name") String name){
        List<QuestionGetDto> questions=questionService.questionsByName(name);
            if (questions.isEmpty()) {
                throw new NotFoundException("Savollar topilmadi");
            }
            model.addAttribute("questions",questions);
            return "question/questions_edit";
        
    }
    @GetMapping("/start-test/question")
    public String startTest(Model model) throws IOException {
        List<QuestionGetDto> questions = questionService.questions();
        if (questions.isEmpty()) {
            throw new NotFoundException("Savollar topilmadi");
        }
        model.addAttribute("questions", questions);
        return "question/questions";
    }
    @PostMapping("/start-test/question")
    public String startTest(@ModelAttribute @Valid StartTest startTest, Model model){
        StartTest result = questionService.startTest(startTest);
        model.addAttribute("result",result);
        AnswerDto answerDto = answerService.get(result.answerId());
        QuestionGetDto getDto = questionService.get(result.questionId());
        model.addAttribute("answer",answerDto);
        model.addAttribute("question",getDto);
        return "question/result";
    }
    @PostMapping("/delete/question")
    public String delete(@RequestParam("id") long id, Model model) {
        questionService.deleteQuestion(id);
        System.out.println("Hello from question delete API");
        List<QuestionGetDto> questions = questionService.questions();
        if (questions.isEmpty()) {
            throw new NotFoundException("Savollar topilmadi");
        }
        model.addAttribute("questions", questions);
        return "question/questions_edit";
    }
    
}
