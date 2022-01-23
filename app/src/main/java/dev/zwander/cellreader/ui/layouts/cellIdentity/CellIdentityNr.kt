package dev.zwander.cellreader.ui.layouts.cellIdentity

import android.os.Build
import android.telephony.CellIdentityNr
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.zwander.cellreader.R
import dev.zwander.cellreader.data.ARFCNInfo
import dev.zwander.cellreader.utils.*

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CellIdentityNr.CellIdentityNr(
    arfcnInfo: List<ARFCNInfo>,
    simple: Boolean,
    advanced: Boolean
) {
    if (advanced) {
        tac.onAvail {
            FormatText(R.string.tac_format, it.toString())
        }
        nci.onAvail {
            FormatText(R.string.nci_format, it.toString())
        }
        pci.onAvail {
            FormatText(
                textId = R.string.pci_format,
                it.toString()
            )
        }
        nrarfcn.onAvail {
            FormatText(
                textId = R.string.nrarfcn_format,
                it.toString()
            )
        }

        val dlFreqs = remember(nrarfcn) { arfcnInfo.map { it.dlFreq } }
        val ulFreqs = remember(nrarfcn) { arfcnInfo.map { it.ulFreq } }

        if (dlFreqs.isNotEmpty()) {
            FormatText(
                textId = R.string.dl_freqs_format,
                dlFreqs.joinToString(", ")
            )
        }

        if (ulFreqs.isNotEmpty()) {
            FormatText(
                textId = R.string.ul_freqs_format,
                ulFreqs.joinToString(", ")
            )
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!additionalPlmns.isNullOrEmpty()) {
                FormatText(
                    textId = R.string.additional_plmns_format,
                    additionalPlmns.joinToString(", ") { it.asMccMnc }
                )
            }
        }
    }
}