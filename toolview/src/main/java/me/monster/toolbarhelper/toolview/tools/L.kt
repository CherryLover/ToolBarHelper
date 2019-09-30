package me.monster.toolbarhelper.toolview.tools

import android.util.Log
import me.monster.toolbarhelper.toolview.BuildConfig

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-24 10:00
 */
class L {
    companion object {
        private const val tag = "ToolBarHelper"
        private val log = BuildConfig.DEBUG

        fun e(tag: String, message: String, throwable: Throwable) {
            if (log) {
                Log.e(tag, message + stackTrace(this), throwable)
            }
        }

        fun e(tag: String, throwable: Throwable) {
            if (log) {
                Log.e(tag, stackTrace(this), throwable)
            }
        }

        fun e(throwable: Throwable) {
            if (log) {
                Log.e(tag, "", throwable)
            }
        }


        fun e(tag: String, message: String) {
            if (log) {
                Log.e(tag, message + stackTrace(this))
            }
        }

        fun e(throwable: Throwable, message: String) {
            if (log) {
                Log.e(tag, message, throwable)
            }
        }

        fun e(message: String) {
            if (log) {
                Log.e(tag, message)
            }
        }

        fun w(tag: String, message: String) {
            if (log) {
                Log.w(tag, message + stackTrace(this))
            }
        }

        fun w(message: String) {
            if (log) {
                Log.w(tag, message)
            }
        }

        fun d(tag: String, message: String) {
            if (log) {
                Log.d(tag, message + stackTrace(this))
            }
        }

        fun d(message: String) {
            if (log) {
                Log.d(tag, message)
            }
        }

        fun i(tag: String, message: String) {
            if (log) {
                Log.i(tag, message + stackTrace(this))
            }
        }

        fun i(message: String) {
            if (log) {
                Log.i(tag, message)
            }
        }

        private fun stackTrace(self: Any): String? {
            if (!log) return null
            val result = StringBuilder()
            result.append(" trace: ")
            var length = 0
            for ((i, it) in Thread.currentThread().stackTrace.withIndex()) {
                if (i < 4 || it.methodName.contains("\$default")) continue
                if (length > 0) result.append(" -> ")
                val simpleClassName = simpleClassName(it.className)
                if (self.javaClass.name != it.className) {
                    result.append(simpleClassName).append(".java:")
                }
                result.append(it.methodName.replace("\$app_debug", ""))
                    .append(" (")
                    .append(simpleClassName)
                    .append(".java:")
                    .append(it.lineNumber)
                    .append(")")
                    .append("\n")
                length++
                if (length > 3) break
            }
            return result.toString()
        }

        private fun simpleClassName(name: String): String {
            return name.substring(name.lastIndexOf(".") + 1)
        }
    }
}