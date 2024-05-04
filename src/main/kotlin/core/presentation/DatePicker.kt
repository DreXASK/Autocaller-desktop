package core.presentation

import androidx.compose.runtime.MutableState
import com.github.lgooddatepicker.components.CalendarPanel
import com.github.lgooddatepicker.components.DatePickerSettings
import com.github.lgooddatepicker.optionalusertools.CalendarListener
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.MouseInfo
import java.awt.event.WindowEvent
import java.awt.event.WindowFocusListener
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.border.LineBorder

object DatePicker {

    fun setDataToState(localDate: MutableState<LocalDate?>) {
        val panel = JPanel()
        val frame = JFrame()

        val listener = object : CalendarListener {
            override fun selectedDateChanged(p0: CalendarSelectionEvent?) {
                localDate.value = p0?.newDate
                frame.dispose()
            }
            override fun yearMonthChanged(p0: YearMonthChangeEvent?) { }
        }

        frame.addWindowFocusListener(object : WindowFocusListener {
            override fun windowGainedFocus(e: WindowEvent?) {}
            override fun windowLostFocus(e: WindowEvent?) { frame.dispose()}
        })

        val settings = DatePickerSettings(Locale("ru", "RU"))
        val calendarPanel = CalendarPanel(settings)
        calendarPanel.selectedDate = localDate.value ?: LocalDate.now()
        calendarPanel.addCalendarListener(listener)
        calendarPanel.setBorder(LineBorder(Color.lightGray))

        panel.add(calendarPanel)

        frame.contentPane.add(panel, BorderLayout.CENTER)
        frame.isUndecorated = true
        frame.location = MouseInfo.getPointerInfo().location
        frame.size = Dimension(400, 400)
        frame.isVisible = true
        frame.pack()
    }

}