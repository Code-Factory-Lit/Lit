package com.example.lit.controller;

import com.example.lit.domain.vo.project.ProjectVO;
import com.example.lit.service.project.LitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/lit/*")
public class LitController {
    private final LitService litService;

    @GetMapping("/write")
    public String write(){
        return "/project/projectWrite";
    }

    @PostMapping("/write")
    public String addWrite(ProjectVO projectVO) {
        log.info("--------------------write---------------------");
        log.info(projectVO.toString());
        log.info(projectVO.getProjectFile() + "");
        litService.register(projectVO);
        return "/main/main";
    }

    @GetMapping("/info/{projectNumber}")
    public String info( @PathVariable("projectNumber") Long projectNumber,  Model model ){
        log.info("--------------------projectInfo---------------------");
        model.addAttribute("projectVO", litService.read(projectNumber));

        return "/project/projectInfo";
    }



}
