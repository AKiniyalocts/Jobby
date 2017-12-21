package com.akiniyalocts.jobby.ui

import android.databinding.BindingAdapter
import android.widget.TextView
import org.sufficientlysecure.htmltextview.HtmlTextView

/**
 * Created by anthonykiniyalocts on 12/19/17.
 */
open class Bindings {

    companion object {
        @JvmStatic @BindingAdapter("app:append_text")
        fun appendText(textView: TextView, text:String){
            textView.text = textView.text.toString().plus(" $text ")
        }

        @JvmStatic @BindingAdapter("app:html_text")
        fun htmlText(textView: HtmlTextView, htmlText: String?){
            htmlText?.let {
                textView.setHtml(it)
            }
        }
    }



}