package com.duyts.tasks.signin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task

class GoogleAuthResultContract : ActivityResultContract<Int, Task<GoogleSignInAccount>?>() {
	override fun createIntent(context: Context, input: Int): Intent =
		getGoogleSignInClient(context).signInIntent.putExtra("input", input)

	override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? =
		when (resultCode) {
			Activity.RESULT_OK -> GoogleSignIn.getSignedInAccountFromIntent(intent)
			else -> null
		}

}

fun getGoogleSignInClient(context: Context): GoogleSignInClient {
	val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//         Request id token if you intend to verify google user from your backend server
//        .requestIdToken(context.getString(R.string.backend_client_id))
		.requestEmail()
		.build()

	return GoogleSignIn.getClient(context, signInOptions)
}