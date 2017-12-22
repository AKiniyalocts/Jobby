package com.akiniyalocts.jobby.ui

import android.databinding.BindingAdapter
import android.widget.TextView
import org.sufficientlysecure.htmltextview.HtmlTextView

/**
 * Created by anthonykiniyalocts on 12/19/17.
 *
 * Custom data binding methods.
 */
open class Bindings {

    companion object {

        /**
         * expose html.setHtml() method for databinding
         *
         * see https://github.com/PrivacyApps/html-textview
         */
        @JvmStatic @BindingAdapter("app:html_text")
        fun htmlText(textView: HtmlTextView, htmlText: String?){
            htmlText?.let {
                textView.setHtml(it)
            }
        }
    }



}