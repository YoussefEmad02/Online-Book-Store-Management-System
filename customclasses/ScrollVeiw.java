package com.example.bookstore.customclasses;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;



public  class ScrollVeiw extends ScrollPane {

    int scrollinSpeed;
    ScrollVeiw(){
        super();
    }
    ScrollVeiw(Pane pane){
        super(pane);
    }
    public ScrollVeiw(Pane pane, int scrollinSpeed){
        super(pane);
        this.scrollinSpeed=scrollinSpeed;
        SetScrollingSpeed();

    }
    void SetContent(Pane pane){
        super.setContent(pane);
    }

    void SetSpeed(int scrollinSpeed){
        this.scrollinSpeed=scrollinSpeed;
        SetScrollingSpeed();
    }

    private void SetScrollingSpeed() {
        super.getContent().setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                double deltaY = scrollEvent.getDeltaY()*scrollinSpeed;
                double height = ScrollVeiw.super.getContent().getBoundsInLocal().getHeight();
                double vvalue = ScrollVeiw.super.getVvalue();
                ScrollVeiw.super.setVvalue(vvalue + -deltaY/height);

                double deltaX = scrollEvent.getDeltaX()*scrollinSpeed;
                double width = ScrollVeiw.super.getContent().getBoundsInLocal().getWidth();
                double hvalue = ScrollVeiw.super.getHvalue();
                ScrollVeiw.super.setHvalue(hvalue + -deltaX/width);
            }
        });
    }


}
