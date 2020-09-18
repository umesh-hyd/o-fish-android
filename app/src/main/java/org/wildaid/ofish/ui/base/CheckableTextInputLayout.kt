package org.wildaid.ofish.ui.base

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.item_checkable_text_input_layout.view.*
import org.wildaid.ofish.R
import org.wildaid.ofish.util.requestKeyBoard

class CheckableTextInputLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var editLayout: TextInputLayout
        get() = edit_layout
        private set(value) {}


    init {
        inflate(context, R.layout.item_checkable_text_input_layout, this)

        context.theme.obtainStyledAttributes(attrs, R.styleable.CheckableTextInputLayout, 0, 0)
            .apply()
            {
                try {
                    edit_layout.hint = getString(R.styleable.CheckableTextInputLayout_textHint)
                    edit_layout_checkbox.text =
                        getString(R.styleable.CheckableTextInputLayout_checkboxHint)
                } finally {
                    recycle()
                }
            }

        edit_layout_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                edit_layout_edit_text.setText(R.string.none)
                edit_layout_edit_text.isEnabled = false
            } else {
                edit_layout_edit_text.text = null
                edit_layout_edit_text.isEnabled = true
                edit_layout_edit_text.requestKeyBoard()
            }
        }
    }
}