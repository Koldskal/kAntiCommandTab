package me.koldskal.kACT.utils.messaging;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


// _Koldskal found these symbols. Why? I still do not know...!
@Getter @RequiredArgsConstructor
public enum Symbols {
    ARROW("➡"),
    CIRCLE("●"),
    ARROW_RIGHT("»"),
    ARROW_LEFT("«"),
    SQUARE("■"),
    BRACKET_1("【"),
    BRACKET_2("】"),
    COMPLETE("✔"),
    INCOMPLETE("✘"),
    LINE("│");


    private final String symbol;
}