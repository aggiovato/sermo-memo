package com.sermomemo.core.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.sermomemo.core.designsystem.theme.SermoMemoTheme
import com.sermomemo.core.designsystem.theme.SermoRadius
import com.sermomemo.core.designsystem.theme.SermoSize
import com.sermomemo.core.designsystem.theme.SermoSpacing
import com.sermomemo.core.designsystem.theme.ThemePreviews

/**
 * Primary action of a screen. There is at most one per screen.
 *
 * While [loading] the button stays enabled-looking but blocks input, so the layout does not jump.
 */
@Composable
fun SermoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: ImageVector? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = SermoSize.ButtonHeight),
        enabled = enabled && !loading,
        shape = SermoRadius.Medium,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SermoSpacing.XSmall),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            when {
                loading -> CircularProgressIndicator(
                    modifier = Modifier.size(SermoSize.IconSmall),
                    strokeWidth = 2.dp,
                )

                icon != null -> Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(SermoSize.IconMedium),
                )
            }
            Text(text)
        }
    }
}

/** Secondary action. Never competes visually with [SermoButton]. */
@Composable
fun SermoOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = SermoSize.ButtonHeight),
        enabled = enabled,
        shape = SermoRadius.Medium,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SermoSpacing.XSmall),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(SermoSize.IconMedium),
                )
            }
            Text(text)
        }
    }
}

@ThemePreviews
@Composable
private fun SermoButtonPreview() {
    SermoMemoTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(SermoSpacing.XSmall)) {
            SermoButton(text = "Guardar", onClick = {})
            SermoOutlinedButton(text = "Cancelar", onClick = {})
        }
    }
}
