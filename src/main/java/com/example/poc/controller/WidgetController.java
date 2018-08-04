package com.example.poc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WidgetController {

    @GetMapping("/widgets")
    public List<Widget> getWidgets() {
        List<Widget> widgets = new ArrayList<>();
        Widget widget = new Widget();
        widget.name = "thing 1";
        widgets.add(widget);
        widget = new Widget();
        widget.name = "thing 2";
        widgets.add(widget);
        return widgets;
    }

    static class Widget {
        public String name;
    }
}
