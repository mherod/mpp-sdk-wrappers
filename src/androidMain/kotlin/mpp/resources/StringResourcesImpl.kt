package mpp.resources

import android.content.Context

actual class StringResourcesImpl(private val context: Context) : StringResources {
    override fun getString(identifier: StringResourceIdentifier): String {
        return context.getString(identifier)
    }
}
