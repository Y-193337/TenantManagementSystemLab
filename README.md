# Tenant Management System

An Android app for landlords to record tenants, see total rent, and call tenants.

## Download
Latest APK: see **Releases** on the right of this page.

## Features
- Register and log in
- Dashboard with number of tenants and total rent
- Add tenants and view all tenants (saved with Room)
- Call a tenant, options menu and navigation drawer

## Architecture
- `ui/`: Activities, Fragments, and TenantViewModel (UI layer)
- `data/`: Tenant, TenantDao, TenantDatabase, TenantRepository (data layer)

Data Flow: Screens → TenantViewModel (LiveData) → TenantRepository → Room Database

## Team
| Stage | Member | Admission No. | Pull request |
|---|---|---|---|
| 1 Lead | TANISHA LAVIDA AMARA| 190128 | https://github.com/Lavida-loca/TenantManagementSystem |
| 2 ViewModel | MICHELLE MUNGAI NJOKI | 193335 | https://github.com/Michelle-M3/TenantManagementSystem2-.git|
| 3 Room | YAMATO MUTUA KATSUKAWA |193337 |https://github.com/Y-193337/TenantManagementSystemLab.git|
| 4 Screens | AHMED ISMAEL FOUZI | 190283| https://github.com |
| 5 Data integration | KABURU RYAN MURIITHI | 189480| https://github.com/muriithiryan/TenantManagementSystem.git |
| 6 Release | Member Name  | 00000 | https://github.com |
