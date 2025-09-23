package ui;

import javax.swing.JPanel;

public abstract class FormPanel extends JPanel {

    public FormPanel() {
        init();        // hook to initialize (child can override if needed)
        applyStyles(); // apply global styles after NetBeans initComponents()
    }

    /**
     * Optional setup logic for the form.
     * NetBeans will call initComponents() in child panels, 
     * so you usually don’t override this.
     */
    protected void init() {}

    /**
     * Apply styles to UI components.
     * Child panels MUST implement this to apply UIStyle.
     */
    protected abstract void applyStyles();
}
