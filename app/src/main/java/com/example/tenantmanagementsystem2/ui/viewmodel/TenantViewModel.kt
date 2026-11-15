package com.example.tenantmanagementsystem2.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tenantmanagementsystem2.data.local.TenantDatabase
import com.example.tenantmanagementsystem2.data.model.Tenant
import com.example.tenantmanagementsystem2.data.repository.TenantRepository
import kotlinx.coroutines.launch

// AndroidViewModel gives us access to the application context.
class TenantViewModel(application: Application) : AndroidViewModel(application) {

    // ── 1. DATABASE AND REPOSITORY ────────────────────────────────

    private val tenantDao = TenantDatabase
        .getDatabase(application)
        .tenantDao()

    private val repository = TenantRepository(tenantDao)

    // Room provides LiveData that automatically updates when data changes.
    val tenants: LiveData<List<Tenant>> = repository.tenants


    // ── 2. TRANSFORMATIONS: DATA FOR THE SCREENS ──────────────────

    val tenantCountText: LiveData<String> = tenants.map { list ->
        "Tenants\n${list.size}"
    }

    val totalRentText: LiveData<String> = tenants.map { list ->
        val total = list.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
        "Total rent\nKSh %,.0f".format(total)
    }

    val allTenantsText: LiveData<String> = tenants.map { list ->
        if (list.isEmpty()) {
            "No tenants yet. Add one from the Dashboard."
        } else {
            list.joinToString("\n\n") { it.summary() }
        }
    }


    // ── 3. ACTIONS THE SCREENS CAN REQUEST ────────────────────────

    fun addTenant(name: String, phone: String, rent: String): Tenant? {

        val cleanName = name.trim()
        val cleanPhone = phone.trim()
        val cleanRent = rent.trim()

        // Validate the input.
        if (cleanName.isEmpty() || cleanPhone.isEmpty() || cleanRent.isEmpty()) {
            return null
        }

        // Create a new tenant.
        val tenant = Tenant(cleanName, cleanPhone, cleanRent)

        // Save the tenant asynchronously through the repository.
        viewModelScope.launch {
            repository.addTenant(tenant)
        }

        // Return the tenant for Safe Args navigation.
        return tenant
    }


    // ── 4. LIFECYCLE LOGS ─────────────────────────────────────────

    init {
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: onCleared")
    }
}