package org.app;

import org.app.functions.GetHoroscope;

public class Main {
    static void main() {
        new appLayout();
        GetHoroscope getHoroscope = new GetHoroscope("aquarius");
    }
}
