package com.cmps312.bankingapp.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Details
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon : ImageVector){
    object TransferList : Screen(route = "transferList", title = "Transfers",
        icon = Icons.Outlined.AccountBalance)

    object TransferDetail : Screen(route = "transferDetail", title = "Transfer Details",
        icon = Icons.Outlined.Details)

    object FundTransfer : Screen(route = "fundTransfer", title = "Fund Transfer",
        icon = Icons.Outlined.Money)

    object Beneficiary : Screen(route = "selectBeneficiary", title = "Select Beneficiary",
        icon = Icons.Outlined.PersonAdd)

    object TransferConfirmation : Screen(route = "transferConfirmation", title = "Transfer Confirmation",
        icon = Icons.Outlined.PersonAdd)

    object AccountDetails : Screen(route = "accountDetails", title = "Account Details",
        icon = Icons.Outlined.Person)
}