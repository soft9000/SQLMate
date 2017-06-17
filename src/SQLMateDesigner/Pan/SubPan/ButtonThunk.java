/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLMateDesigner.Pan.SubPan;

import java.awt.event.ActionListener;

/**
 *
 * @author profnagy
 */
public class ButtonThunk {
    private final String Text;
    private final ActionListener Action;

    public ButtonThunk(String Text, ActionListener Action) {
        this.Text = Text;
        this.Action = Action;
    }

    /**
     * @return the Text
     */
    public String getText() {
        return Text;
    }

    /**
     * @return the Action
     */
    public ActionListener getAction() {
        return Action;
    }

}
