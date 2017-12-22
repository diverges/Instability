package com.cosmosengine.interfaces;

import java.awt.event.MouseEvent;

/**
 * Implement this interface in any class to add the ability to click on stuff. (Remember to add it on the GameCanvas)
 */
public interface Clickable {

    void mousePressed(MouseEvent event);

    void mouseReleased(MouseEvent event);

    void mouseEntered(MouseEvent event);

    void mouseExited(MouseEvent event);

    void mouseDragged(MouseEvent event);

    void mouseMoved(MouseEvent event);

    void mouseClicked(MouseEvent event);

}
