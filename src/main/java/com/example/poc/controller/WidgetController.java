package com.example.poc.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WidgetController {

    @PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_TKN1')")
    @GetMapping("/tkn1/user/widgets")
    public List<Widget> getWidgetsForTkn1AndUser() {

        List<Widget> widgets = new ArrayList<>();
        Widget widget = new Widget();
        widget.name = "thing 1";
        widgets.add(widget);
        widget = new Widget();
        widget.name = "thing 2";
        widgets.add(widget);
        return widgets;
    }

    @PreAuthorize("hasRole('SUPER_USER') AND hasRole('ROLE_TKN1')")
    @GetMapping("/tkn1/superuser/widgets")
    public List<Widget> getWidgetsForTkn1AndSuperUser() {

        System.out.println("####### " + SecurityContextHolder.getContext().getAuthentication());

        List<Widget> widgets = new ArrayList<>();
        Widget widget = new Widget();
        widget.name = "thing 3";
        widgets.add(widget);
        return widgets;
    }

    static class Widget {
        public String name;
    }
}
