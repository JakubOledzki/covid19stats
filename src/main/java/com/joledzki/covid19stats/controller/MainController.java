package com.joledzki.covid19stats.controller;

import com.google.gson.Gson;
import com.joledzki.covid19stats.model.All;
import com.joledzki.covid19stats.model.Country;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Controller
public class MainController {

    public All getCovid() throws IOException {
        URL url = new URL("https://api.covid19api.com/summary");
        InputStreamReader reader = new InputStreamReader(url.openStream());

        All all = new Gson().fromJson(reader, All.class);
        System.out.println(all.getCountries().toString());
        return all;
    }

    @RequestMapping("/")
    public String getTest(Model model) throws IOException {
        model.addAttribute("global",getCovid().getGlobal());
        model.addAttribute("countries",getCovid().getCountries());
        return "index";
    }

}
