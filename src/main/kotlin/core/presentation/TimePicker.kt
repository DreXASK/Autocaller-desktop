package core.presentation

import androidx.compose.runtime.MutableState
import com.github.lgooddatepicker.components.TimePicker
import com.github.lgooddatepicker.components.TimePickerSettings
import java.awt.*
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import java.awt.event.WindowEvent
import java.awt.event.WindowFocusListener
import java.beans.PropertyChangeEvent
import java.beans.PropertyVetoException
import java.beans.VetoableChangeListener
import java.time.LocalTime
import java.util.*
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JWindow


object TimePicker {

    fun setTimeToState(localTime: MutableState<LocalTime?>) {
        val panel = JPanel()
        val frame = JFrame()

        frame.addWindowFocusListener(object : WindowFocusListener {
            override fun windowGainedFocus(e: WindowEvent?) {}
            override fun windowLostFocus(e: WindowEvent?) { frame.dispose() }
        })

        val timeSettings = TimePickerSettings(Locale("ru", "RU"))
        timeSettings.displayToggleTimeMenuButton = false;
        timeSettings.displaySpinnerButtons = true
        timeSettings.setInitialTimeToNow()
        val timePicker = TimePicker(timeSettings)

        timePicker.addTimeChangeListener {
            localTime.value = it.newTime
        }

        panel.add(timePicker)

        frame.contentPane.add(panel, BorderLayout.CENTER)
        frame.isUndecorated = true
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.location = MouseInfo.getPointerInfo().location
        frame.size = Dimension(400, 400)
        frame.isVisible = true
        frame.pack()
    }

}